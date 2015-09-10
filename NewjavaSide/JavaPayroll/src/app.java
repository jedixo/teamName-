

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.sun.org.apache.bcel.internal.generic.NEW;

import controll.AddEmployee;
import controll.Database;
import controll.Emplist;
import controll.Employee;
import controll.ViewEmployees;
import view.MainDisplay;
import view.MainFrame;


public class app {
	
	public static MainFrame mainFrame;
	private static Database empDatabase;
	private static MainDisplay mainDisplay;
	private static ArrayList<Employee> empList = new ArrayList<>();
	

	public static void main(String[] args) {
		mainDisplay = new MainDisplay();
		mainFrame = new MainFrame(mainDisplay);
		empDatabase = new Database("sql6.freemysqlhosting.net/sql689509", "sql689509",
				"lA7*wL7!");
		addEmployeesToList();
		
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
				case "Add Employee":
					//TODO: change so addEmployee creates an employee, the employee is added to employelist which is then added 
					//to the databse
					new AddEmployee(empDatabase);
					break;
				case "View Employees":
					new ViewEmployees(empList);
					break;
				}
			}
		});
		
	}

	// TODO: add more values
	private static void addEmployeesToList() {
		try {
			ResultSet rs = empDatabase.getTable("employees");
			while (rs.next()){
				Employee employee = new Employee(rs.getString("first_name"), rs.getString("last_name"));
				employee.setId(rs.getInt("id"));
				empList.add(employee);
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
	}

}
