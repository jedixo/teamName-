package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class LoadingBar extends JFrame{
	private JProgressBar pbar;
	private static final int MINIMUM = 0;
	private static final int MAXIMUM = 100;
	private final JPanel panel;
	
	public LoadingBar(String text) {
		
		panel = new JPanel();
		pbar = new JProgressBar();
		pbar.setMinimum(MINIMUM);
		pbar.setMaximum(MAXIMUM);
		panel.add(pbar);
		pbar.setValue(1);
		pbar.setStringPainted(true);
		pbar.setString(text);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void updateBar(int value) {
		pbar.setValue(value);
	}
	
	public void updateBar(int value,String text) {
		pbar.setValue(value);
		pbar.setString(text);
	}

}
