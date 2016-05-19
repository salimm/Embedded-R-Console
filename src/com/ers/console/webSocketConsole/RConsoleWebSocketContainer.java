package com.ers.console.webSocketConsole;

import com.ers.console.webSocketConsole.msg.RConsoleResponse;

public interface RConsoleWebSocketContainer {
	
	public void sendResponse(RConsoleResponse response);
	
	public void close();
}
