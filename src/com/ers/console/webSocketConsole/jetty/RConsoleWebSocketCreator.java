package com.ers.console.webSocketConsole.jetty;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import com.ers.console.webSocketConsole.RWebSocketConsole;
public class RConsoleWebSocketCreator implements WebSocketCreator {

	private RWebSocketConsole console;

	public RConsoleWebSocketCreator(RWebSocketConsole console) {
		this.console = console;
	}

	@Override
	public Object createWebSocket(ServletUpgradeRequest arg0,
			ServletUpgradeResponse arg1) {
		return new RConsoleWebSocket(console);
	}

}
