package com.ers.console.webSocketConsole.jetty;


import java.io.IOException;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import com.ers.console.webSocketConsole.RConsoleWebSocketContainer;
import com.ers.console.webSocketConsole.RWebSocketConsole;
import com.ers.console.webSocketConsole.msg.RConsoleCommand;
import com.ers.console.webSocketConsole.msg.RConsoleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RConsoleWebSocket extends WebSocketAdapter implements
		RConsoleWebSocketContainer {

	RWebSocketConsole console;

	public RConsoleWebSocket(RWebSocketConsole console) {
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
