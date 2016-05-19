package com.ers.console.webSocketConsole.msg;

public enum RConsoleResponseType {

	PRINT_MESSAGE, SHOW_MESSAGE, PRINT_ERROR, SHOW_ERROR, CLOSE, MISC_ACTION, ENABLE_STATUS;

	public RConsoleResponseType parse(String txt) {
		if (txt == null)
			return null;
		if (txt.equals(PRINT_MESSAGE.toString())) {
			return PRINT_MESSAGE;
		} else if (txt.equals(SHOW_MESSAGE.toString())) {
			return SHOW_MESSAGE;
		} else if (txt.equals(PRINT_ERROR.toString())) {
			return PRINT_ERROR;
		} else if (txt.equals(SHOW_ERROR.toString())) {
			return SHOW_ERROR;
		} else if (txt.equals(CLOSE.toString())) {
			return CLOSE;
		} else if (txt.equals(MISC_ACTION.toString())) {
			return MISC_ACTION;
		} else if (txt.equals(ENABLE_STATUS.toString())) {
			return ENABLE_STATUS;
		}
		return null;
	}

}
