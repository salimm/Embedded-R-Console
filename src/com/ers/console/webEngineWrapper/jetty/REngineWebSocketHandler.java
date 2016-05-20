package com.ers.console.webEngineWrapper.jetty;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.ers.console.webEngineWrapper.REngineWebWrapper;

public class REngineWebSocketHandler extends WebSocketHandler {

	private REngineWebWrapper console;

	public REngineWebSocketHandler(REngineWebWrapper console) {
		this.console = console;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {

		factory.setCreator(new REngineWebSocketCreator(console));
	}
}