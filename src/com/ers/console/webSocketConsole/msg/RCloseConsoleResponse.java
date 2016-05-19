package com.ers.console.webSocketConsole.msg;


public class RCloseConsoleResponse extends RConsoleResponse{

	public RCloseConsoleResponse() {
		super("R Console is closed...", RConsoleResponseType.CLOSE, RConsoleResponseStatus.OK);
	}

}
