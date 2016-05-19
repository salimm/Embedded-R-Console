package examples;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.ers.console.RConsole;
import com.ers.console.RConsoleEventListener;
import com.ers.console.java.RSimpleConsole;
import com.ers.errors.CanNotLoadRException;
import com.java.swing.components.JConsole;

public class SwingExample {


	public static void main(String[] args) throws CanNotLoadRException {
		// args configure UI
		JFrame frame = new JFrame();
		frame.setSize(600, 300);
		JConsole  jc = new JConsole();
		frame.getContentPane().add(new JScrollPane(jc));
		frame.setVisible(true);

		// configure R console
		RSimpleConsole console = new RSimpleConsole(jc.createInputStream(),
				jc.createOutputStream(), new RSwingConsoleEventListener(jc,frame));
		console.init();
		console.run();

	}


}

class RSwingConsoleEventListener implements RConsoleEventListener{
	private JConsole console;
	private JFrame frame;

	public RSwingConsoleEventListener(JConsole console, JFrame frame) {
		this.console = console;
		this.frame = frame;
	}

	@Override
	public void handleEnableEvent(boolean enable) {
		console.setEnabled(enable);
	}

	@Override
	public void handleTerminate() {
		frame.setVisible(false);
	}
	
}
