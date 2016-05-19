package com.ers;

public enum CommandType {

	QUIT;

	public static CommandType parseString(String cmd) {
		cmd = cmd.trim();
		if (cmd.equals("quit")) {
			return QUIT;
		}
		return null;
	}

}
