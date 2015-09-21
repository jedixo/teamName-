

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controll.Database;
import controll.EmpList;
import view.LoadingBar;
import view.MainDisplay;
import view.MainFrame;
import view.ViewEmployees;


public class app {
	
	private static MainFrame mainFrame;
	private static Database empDatabase;
	private static MainDisplay mainDisplay;
	private static EmpList empList;
	

	public static void main(String[] args) {
		
		LoadingBar load = new LoadingBar("Connecting");
		empDatabase = new Database("sql6.freemysqlhosting.net/sql689509", "sql689509",
				"lA7*wL7!");
		load.updateBar(50,"Querying Database");
		empList = new EmpList(empDatabase.getTable("employees"));
		load.updateBar(100,"Loading UI");
		load.dispose();
		
		mainDisplay = new MainDisplay();
		mainDisplay.addButtonHandler(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				
				switch (e.getActionCommand()) {
				case "View Employees":
					new ViewEmployees(empList, empDatabase);
					break;
				}
			}
		});
		mainFrame = new MainFrame(mainDisplay);
		mainFrame.addMenuHandler(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "Exit":
					mainFrame.dispose();
					break;
				}
			}
		});	
	}
}
