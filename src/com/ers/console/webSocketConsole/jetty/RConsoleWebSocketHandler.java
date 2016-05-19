package com.ers.console.webSocketConsole.jetty;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.ers.console.webSocketConsole.RWebSocketConsole;

public class RConsoleWebSocketHandler extends WebSocketHandler {

	private RWebSocketConsole console;

	public RConsoleWebSocketHandler(RWebSocketConsole console) {
		this.console = console;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {

		factory.setCreator(new RConsoleWebSocketCreator(console));
	}
}