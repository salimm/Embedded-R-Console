package com.ers.console.webEngineWrapper.jetty;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import com.ers.console.webEngineWrapper.REngineWebWrapper;
public class REngineWebSocketCreator implements WebSocketCreator {

	private REngineWebWrapper console;

	public REngineWebSocketCreator(REngineWebWrapper console) {
		this.console = console;
	}

	@Override
	public Object createWebSocket(ServletUpgradeRequest arg0,
			ServletUpgradeResponse arg1) {
		return new REngineWebSocket(console);
	}

}
