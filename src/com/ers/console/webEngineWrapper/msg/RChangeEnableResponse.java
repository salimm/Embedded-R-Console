package com.ers.console.webEngineWrapper.msg;

public class RChangeEnableResponse extends RConsoleResponse {

	public RChangeEnableResponse(boolean enable) {
		super(enable + "", RConsoleResponseType.ENABLE_STATUS,
				RConsoleResponseStatus.OK);
	}

}
