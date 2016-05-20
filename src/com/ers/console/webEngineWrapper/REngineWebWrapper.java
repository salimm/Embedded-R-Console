package com.ers.console.webEngineWrapper;

import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

import com.ers.console.REngineWrapper;
import com.ers.console.REngineWrapperListener;
import com.ers.console.webEngineWrapper.msg.RCloseConsoleResponse;
import com.ers.console.webEngineWrapper.msg.RConsoleCommand;
import com.ers.console.webEngineWrapper.msg.RConsoleResponse;
import com.ers.console.webEngineWrapper.msg.RConsoleResponseStatus;
import com.ers.console.webEngineWrapper.msg.RConsoleResponseType;
import com.ers.errors.CanNotLoadRException;

/**
 * communicates through Http
 * 
 * @author Salim
 *
 */
public class REngineWebWrapper extends REngineWrapper {

	/**
	 * holds instance of R Engine from JRI
	 */
	private Rengine rEngine;

	/**
	 * holds instance of R Console Event Listener
	 */
	private REngineWrapperListener eventListener;

	/**
	 * holds the instance of CollbackHandler for R engine
	 */
	private RMainLoopCallbacks rCallbackHandler;

	/**
	 * holds pointer to socket being used by console for communication
	 */
	private REngineWebWrapperSocketContainer socket;

	public REngineWebWrapper() {
		REngineWebWrapperListener listner = new REngineWebWrapperListener(this);
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
		getSocket().sendResponse(new RCloseConsoleResponse());
		getSocket().close();
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

	public REngineWrapperListener getEventListener() {
		return eventListener;
	}

	public void setEventListener(REngineWrapperListener eventListener) {
		this.eventListener = eventListener;
	}

	public RMainLoopCallbacks getrCollbackHandler() {
		return rCallbackHandler;
	}

	public void setrCollbackHandler(RMainLoopCallbacks rCollbackHandler) {
		this.rCallbackHandler = rCollbackHandler;
	}

	public void sendResponse(RConsoleResponse response) {
		if (getSocket() != null)
			getSocket().sendResponse(response);
	}

	public REngineWebWrapperSocketContainer getSocket() {
		return socket;
	}

	public void setSocket(REngineWebWrapperSocketContainer socket) {
		this.socket = socket;
	}

	public void configureSocket(REngineWebWrapperSocketContainer socket) {
		this.socket = socket;

	}
}
