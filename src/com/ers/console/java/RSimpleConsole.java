package com.ers.console.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

import com.ers.CommandType;
import com.ers.console.RConsole;
import com.ers.console.RConsoleEventListener;
import com.ers.console.webSocketConsole.msg.RConsoleResponseStatus;
import com.ers.errors.CanNotLoadRException;

public class RSimpleConsole extends RConsole implements RMainLoopCallbacks {

	private InputStream inStream;
	private Rengine rEngine;
	private Scanner scanner;
	private OutputStream outStream;
	private RConsoleEventListener eventListener;

	public RSimpleConsole(InputStream inStream, OutputStream outStream,
			RConsoleEventListener eventListener) {
		this.outStream = outStream;
		this.eventListener = eventListener;
		this.setInStream(inStream);
	}

	public void run() {
		String line = "";
		CommandType cmd = null;
		do {
			line = readNextLine();
			cmd = CommandType.parseString(line);
			if (cmd == null)
				getEngine().eval(line);
		} while (cmd != CommandType.QUIT);
		printMessage(MSG_PRINT_R_ENGINE_CLOSED, RConsoleResponseStatus.OK);
		setEnable(false);
		closeConsole();
	}

	@Override
	public void init() throws CanNotLoadRException {
		String[] Rargs = { "--vanilla" };
		rEngine = new Rengine(Rargs, false, this);
		if (!rEngine.waitForR()) {
			throw new CanNotLoadRException();
		}
		scanner = new Scanner(inStream);
	}

	/**
	 * reads the next line from input based on the implementation. It can be
	 * from a input stream or it can be from network depending on the
	 * implementation
	 * 
	 * @return
	 */
	protected String readNextLine() {
		return scanner.nextLine();
	}

	@Override
	protected Rengine getEngine() {
		return this.rEngine;
	}

	@Override
	public void closeConsole() {
		rEngine.end();
		scanner.close();
		try {
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void rBusy(Rengine arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public String rChooseFile(Rengine arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rFlushConsole(Rengine arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rLoadHistory(Rengine arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public String rReadConsole(Rengine arg0, String arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rSaveHistory(Rengine arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rShowMessage(Rengine arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rWriteConsole(Rengine arg0, String arg1, int arg2) {
		try {
			outStream.write(arg1.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public InputStream getInStream() {
		return inStream;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}

	@Override
	protected void showMessage(String msg, RConsoleResponseStatus status) {

	}

	@Override
	protected void printMessage(String msg, RConsoleResponseStatus status) {
		try {
			outStream.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void setEnable(boolean enable) {
		eventListener.handleEnableEvent(enable);
	}

}
