package com.ers.console.webSocketConsole.msg;


/**
 * RConsoleCommand to store command information
 * @author Salim
 *
 */
public class RConsoleCommand {
	
	private String command;

	/**
	 * dummy for json to Java object
	 */
	public RConsoleCommand() {
	}
	
	public RConsoleCommand(String cmd) {
		this.setCommand(cmd);
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
