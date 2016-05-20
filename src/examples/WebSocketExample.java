package examples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;

import com.ers.console.webSocketConsole.RWebSocketConsole;
import com.ers.console.webSocketConsole.jetty.RConsoleWebSocketHandler;

/**
 * 
 * @author Salim
 *
 */
public class WebSocketExample {
	public static void main(String[] args) throws Exception {

		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);
        
		// setting the context routes
		HandlerCollection handlers = new HandlerCollection();

		// handles home
		ContextHandler index = new ContextHandler("/");
		index.setHandler(new HomeHandler());
		handlers.addHandler(index);
		
		// handling assets
		ContextHandler assets = new ContextHandler("/assets");
		assets.setHandler(new AssetsHandler());
		handlers.addHandler(assets);
		
		RWebSocketConsole console = new RWebSocketConsole();
		console.init();
		RConsoleWebSocketHandler webSocketHandler = new RConsoleWebSocketHandler(
				console);
		webSocketHandler.setHandler(new DefaultHandler());
		ContextHandler consoleHandler = new ContextHandler();
		consoleHandler.setContextPath("/console");
		consoleHandler.setHandler(webSocketHandler);
		handlers.addHandler(consoleHandler);
		
		//start server
		server.setHandler(handlers);
		server.start();
		server.join();
	}
}


/**
 * 
 * AbstractVipeHandler
 * 
 * @author salimm
 *
 */
class HomeHandler extends AbstractHandler{


	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (target.equals("/")) {
			response.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);
			response.setContentType("text/html;charset=utf-8");
			String text = new String(Files.readAllBytes(Paths.get("www/index.html")), StandardCharsets.UTF_8);
			response.getWriter().print(text);
		}

	}

	public static boolean isThisMyIpAddress(InetAddress addr) {

		if (addr.isAnyLocalAddress() || addr.isLoopbackAddress())
			return true;

		try {
			return NetworkInterface.getByInetAddress(addr) != null;
		} catch (SocketException e) {
			return false;
		}
	}


}

class AssetsHandler extends AbstractHandler {

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		baseRequest.setHandled(true);
		try {
			// reading file
			String text = new String(Files.readAllBytes(Paths.get("www/" + target.substring(1))),
					StandardCharsets.UTF_8);
			// if file doesn't exists throws error and goes to catch. Otherwise,
			// sets OK
			response.setStatus(HttpServletResponse.SC_OK);
			// checks file type t print
			if (target.endsWith("css"))
				response.setContentType("text/css");
			else if (target.endsWith("js"))
				response.setContentType("text/js");
			else
				response.setContentType("text/html;charset=utf-8");
			// writing content
			response.getWriter().print(text);
		} catch (FileNotFoundException e) {
			// otherwise status is not found
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}