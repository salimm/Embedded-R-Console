package com.ers.console.webSocketConsole;

import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

import com.ers.console.RConsole;
import com.ers.console.RConsoleEventListener;
import com.ers.console.webSocketConsole.msg.RCloseConsoleResponse;
import com.ers.console.webSocketConsole.msg.RConsoleCommand;
import com.ers.console.webSocketConsole.msg.RConsoleResponse;
import com.ers.console.webSocketConsole.msg.RConsoleResponseStatus;
import com.ers.console.webSocketConsole.msg.RConsoleResponseType;
import com.ers.errors.CanNotLoadRException;

/**
 * communicates through Http
 * 
 * @author Salim
 *
 */
public class RWebSocketConsole extends RConsole {

	/**
	 * holds instance of R Engine from JRI
	 */
	private Rengine rEngine;

	/**
	 * holds instance of R Console Event Listener
	 */
	private RConsoleEventListener eventListener;

	/**
	 * holds the instance of CollbackHandler for R engine
	 */
	private RMainLoopCallbacks rCallbackHandler;

	/**
	 * holds pointer to socket being used by console for communication
	 */
	private RConsoleWebSocketContainer socket;

	public RWebSocketConsole() {
		RConsoleWebSocketListener listner = new RConsoleWebSocketListener(this);
		this.setEventListener(listner);
		this.setrCollbackHandler(listner);
	}

	public void eval(RConsoleCommand cmd) {
		rEngine.eval(cmd.getCommand());
	}

	@Override
	public void init() throws CanNotLoadRException {
		String[] Rargs = { "--vanilla" };
		rEngine = new Rengine(Rargs, false, getrCollbackHandler());
		if (!rEngine.waitForR()) {
			throw new CanNotLoadRException();
		}
	}

	@Override
	public void closeConsole() {
		rEngine.end();
		socket.sendResponse(new RCloseConsoleResponse());
		socket.close();
	}

	@Override
	protected Rengine getEngine() {
		return rEngine;
	}

	@Override
	protected void showMessage(String msg, RConsoleResponseStatus status) {
		RConsoleResponse resp = new RConsoleResponse(msg,
				RConsoleResponseType.SHOW_MESSAGE, status);
		sendResponse(resp);
	}

	@Override
	protected void printMessage(String msg, RConsoleResponseStatus status) {
		RConsoleResponse resp = new RConsoleResponse(msg,
				RConsoleResponseType.PRINT_MESSAGE, status);
		sendResponse(resp);
	}

	@Override
	protected void setEnable(boolean enable) {
		this.eventListener.handleEnableEvent(enable);
	}

	public RConsoleEventListener getEventListener() {
		return eventListener;
	}

	public void setEventListener(RConsoleEventListener eventListener) {
		this.eventListener = eventListener;
	}

	public RMainLoopCallbacks getrCollbackHandler() {
		return rCallbackHandler;
	}

	public void setrCollbackHandler(RMainLoopCallbacks rCollbackHandler) {
		this.rCallbackHandler = rCollbackHandler;
	}

	public void sendResponse(RConsoleResponse response) {
		socket.sendResponse(response);
	}
}
