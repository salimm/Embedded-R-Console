package com.ers.console.webEngineWrapper.jetty;


import java.io.IOException;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import com.ers.console.webEngineWrapper.REngineWebWrapper;
import com.ers.console.webEngineWrapper.REngineWebWrapperSocketContainer;
import com.ers.console.webEngineWrapper.msg.RConsoleCommand;
import com.ers.console.webEngineWrapper.msg.RConsoleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class REngineWebSocket extends WebSocketAdapter implements
		REngineWebWrapperSocketContainer {

	REngineWebWrapper console;

	public REngineWebSocket(REngineWebWrapper console) {
		this.console = console;
		console.configureSocket(this);
	}

	@Override
	public void onWebSocketText(String msg) {
		ObjectMapper mapper = new ObjectMapper();
		RConsoleCommand cmd;
		try {
			cmd = mapper.readValue(msg, RConsoleCommand.class);
			console.eval(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendResponse(RConsoleResponse response) {
		try {
			getRemote().sendString(response.toJSON());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			getSession().disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
