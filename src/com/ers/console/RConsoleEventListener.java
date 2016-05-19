package com.ers.console;

public interface RConsoleEventListener {
	
	/**
	 * is called when the RConsole wants to disable/enable interface of any kind
	 * @param disable
	 */
	public void handleEnableEvent(boolean enable);
	
	/**
	 * 
	 * Called when the RConsole wants to terminate and completely close interface
	 * 
	 */
	public void handleTerminate();
	
	
}
