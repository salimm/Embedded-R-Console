package com.ers.console.webEngineWrapper;

import com.ers.console.webEngineWrapper.msg.RConsoleResponse;

public interface REngineWebWrapperSocketContainer {
	
	public void sendResponse(RConsoleResponse response);
	
	public void close();
}
