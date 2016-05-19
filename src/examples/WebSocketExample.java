package examples;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;

import com.ers.console.webSocketConsole.RWebSocketConsole;
import com.ers.console.webSocketConsole.jetty.RConsoleWebSocketHandler;

/**
 * 
 * @author Salim
 *
 */
public class WebSocketExample {
	public static void main(String[] args) throws Exception {
		RWebSocketConsole console = new RWebSocketConsole();
		
		Server server = new Server(8080);
		RConsoleWebSocketHandler webSocketHandler = new RConsoleWebSocketHandler(console);
		webSocketHandler.setHandler(new DefaultHandler());
		server.setHandler(webSocketHandler);
		server.start();
		server.join();
	}
}