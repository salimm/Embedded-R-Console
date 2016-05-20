package com.ers.console.webEngineWrapper.msg;


public class RCloseConsoleResponse extends RConsoleResponse{

	public RCloseConsoleResponse() {
		super("R Console is closed...", RConsoleResponseType.CLOSE, RConsoleResponseStatus.OK);
	}

}
