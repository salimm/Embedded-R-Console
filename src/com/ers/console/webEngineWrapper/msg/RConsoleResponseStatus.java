package com.ers.console.webEngineWrapper.msg;

public enum RConsoleResponseStatus {
	OK, ERROR, WARNING;

	public RConsoleResponseStatus parse(String txt) {
		if (txt == null)
			return null;
		if (txt.equals(OK.toString())) {
			return OK;
		} else if (txt.equals(ERROR.toString())) {
			return ERROR;
		} else if (txt.equals(WARNING.toString())) {
			return WARNING;
		}
		return null;
	}
}
