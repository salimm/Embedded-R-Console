package examples;

import com.ers.console.REngineWrapperListener;
import com.ers.console.java.RCLIWrapper;
import com.ers.errors.CanNotLoadRException;

public class ExampleConsole {

	public static void main(String[] args) throws CanNotLoadRException {
		RCLIWrapper console = new RCLIWrapper(System.in, System.out,
				new RExampleEventListener());
		console.init();
		console.run();
	}
}

class RExampleEventListener implements REngineWrapperListener {

	@Override
	public void handleEnableEvent(boolean enable) {
		// can't disable java default console
	}

	@Override
	public void handleTerminate() {
		// terminating java program
		System.exit(0);
	}

}