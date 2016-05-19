package com.ers.console.webSocketConsole.msg;

public class RChangeEnableResponse extends RConsoleResponse {

	public RChangeEnableResponse(boolean enable) {
		super(enable + "", RConsoleResponseType.ENABLE_STATUS,
				RConsoleResponseStatus.OK);
	}

}
