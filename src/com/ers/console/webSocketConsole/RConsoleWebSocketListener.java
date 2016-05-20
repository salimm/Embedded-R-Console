package com.ers.console.webSocketConsole;

import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

import com.ers.console.RConsoleEventListener;
import com.ers.console.webSocketConsole.msg.RChangeEnableResponse;
import com.ers.console.webSocketConsole.msg.RConsoleResponse;
import com.ers.console.webSocketConsole.msg.RConsoleResponseStatus;
import com.ers.console.webSocketConsole.msg.RConsoleResponseType;

public class RConsoleWebSocketListener implements RMainLoopCallbacks,
		RConsoleEventListener {

	private RWebSocketConsole console;

	
	public RConsoleWebSocketListener(RWebSocketConsole console) {
		this.console = console;
	}

	@Override
	public void rBusy(Rengine arg0, int arg1) {

	}

	@Override
	public String rChooseFile(Rengine arg0, int arg1) {
		return null;
	}

	@Override
	public void rFlushConsole(Rengine arg0) {

	}

	@Override
	public void rLoadHistory(Rengine arg0, String arg1) {

	}

	@Override
	public String rReadConsole(Rengine arg0, String arg1, int arg2) {
		return null;
	}

	@Override
	public void rSaveHistory(Rengine arg0, String arg1) {

	}

	@Override
	public void rShowMessage(Rengine engine, String txt) {
		console.sendResponse(new RConsoleResponse(txt,
				RConsoleResponseType.SHOW_MESSAGE, RConsoleResponseStatus.OK));

	}

	@Override
	public void rWriteConsole(Rengine engine, String txt, int oType) {
		RConsoleResponseStatus status = RConsoleResponseStatus.OK;
		if (oType == 1)
			status = RConsoleResponseStatus.ERROR;

		console.sendResponse(new RConsoleResponse(txt,
				RConsoleResponseType.PRINT_MESSAGE, status));

	}

	@Override
	public void handleEnableEvent(boolean enable) {
		console.sendResponse(new RChangeEnableResponse(enable));
	}

	@Override
	public void handleTerminate() {
		// TODO Auto-generated method stub

	}

}
