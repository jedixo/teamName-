

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import controll.Database;
import controll.EmpList;
import controll.TimeCardList;
import view.LoadingBar;
import view.MainDisplay;
import view.MainFrame;
import view.ViewEmployees;
import view.ViewTimeCards;


public class app {
	
	private static MainFrame mainFrame;
	private static Database database;
	private static MainDisplay mainDisplay;
	private static EmpList empList;
	private static TimeCardList timeCardList;
	
	

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){}
		
		LoadingBar load = new LoadingBar("Connecting");
		database = new Database("sql6.freemysqlhosting.net/sql689509", "sql689509",
				"lA7*wL7!");
		if (database.isConnected) {
			load.updateBar(33,"Querying Employees");
			empList = new EmpList(database.getTable("employees"));
			load.updateBar(66,"Querying Timecards");
			timeCardList = new TimeCardList(database.getTable("time_card"));
			load.updateBar(100,"Loading UI");
			load.dispose();
		
			mainDisplay = new MainDisplay();
		
			mainDisplay.addButtonHandler(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(e.getActionCommand());
				
					switch (e.getActionCommand()) {
					case "Employees":
						new ViewEmployees(empList, database);
						break;
					case "Timecards":
						new ViewTimeCards(timeCardList, database);
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
		} else {
			load.dispose();
			}
	}

}
