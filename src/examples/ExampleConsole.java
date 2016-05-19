package examples;

import com.ers.console.RConsoleEventListener;
import com.ers.console.java.RSimpleConsole;
import com.ers.errors.CanNotLoadRException;

public class ExampleConsole {

	public static void main(String[] args) throws CanNotLoadRException {
		RSimpleConsole console = new RSimpleConsole(System.in, System.out,
				new RExampleEventListener());
		console.init();
		console.run();
	}
}

class RExampleEventListener implements RConsoleEventListener {

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