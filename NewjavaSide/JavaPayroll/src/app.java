

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controll.Database;
import controll.Employee;
import view.LoadingBar;
import view.MainDisplay;
import view.MainFrame;
import view.ViewEmployees;


//TODO:
// Change integer paramaters to corret transaltion
//     - view emp
//     - add emp
//     - mod emp
public class app {
	
	public static MainFrame mainFrame;
	private static Database empDatabase;
	private static MainDisplay mainDisplay;
	private static ArrayList<Employee> empList = new ArrayList<>();
	

	public static void main(String[] args) {
		LoadingBar load = new LoadingBar("Connecting");
		empDatabase = new Database("sql6.freemysqlhosting.net/sql689509", "sql689509",
				"lA7*wL7!");
		load.updateBar(50,"Querying Database");
		addEmployeesToList();
		load.updateBar(100,"Loading UI");
		load.dispose();
		mainDisplay = new MainDisplay();
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
		
	}

	private static void addEmployeesToList() {
		try {
			ResultSet rs = empDatabase.getTable("employees");
			while (rs.next()){
				Employee employee = new Employee(rs.getString("first_name"), rs.getString("last_name"));
				employee.setId(rs.getInt("id"));
				employee.setAddress(rs.getString("Address"));
				employee.setPayType(rs.getInt("pay_type"));
				employee.setPayDelivery(rs.getInt("pay_delivery"));
				employee.setUnion(rs.getString("Emp_Union"));
				employee.setSalary(rs.getInt("Salary"));
				empList.add(employee);
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
	}

}
