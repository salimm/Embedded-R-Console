package com.ers.console;


import org.rosuda.JRI.Rengine;

import com.ers.console.webEngineWrapper.msg.RConsoleResponseStatus;
import com.ers.errors.CanNotLoadRException;
import com.ers.utils.Constants;

/**
 * abstract class for requirements of R console classes
 * 
 * @author Salim
 *
 */
public abstract class REngineWrapper  implements Constants {

	public abstract void init() throws CanNotLoadRException;

//	public abstract void run();

	/**
	 * used to determine whether input is enabled for user
	 * 
	 * @param disable
	 */
	protected abstract void setEnable(boolean enable);

	/**
	 * to show a message. It can have same functionality as the printMessaage if
	 * console or it can have different functionalities
	 * 
	 * @param msg
	 */
	protected abstract void showMessage(String msg, RConsoleResponseStatus status);

	/**
	 * Printing a message in R console
	 * 
	 * @param msg
	 */
	protected abstract void printMessage(String msg, RConsoleResponseStatus status);

	/**
	 * Closing console. Terminates permanently
	 */
	public abstract void closeConsole();

	/**
	 * returns the R engine used
	 */
	protected abstract Rengine getEngine();

	

}
