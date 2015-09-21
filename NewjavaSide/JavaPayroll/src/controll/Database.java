
package controll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private Connection connection = null;
	
	public Database(String host, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://" + host, user, password);
			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet getTable(String table) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
			return rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	


	public void addEmpData(Employee employee) {
		try {
			String sql = "INSERT INTO `employees`(`id`, `first_name`, `last_name`, `Address`, `pay_type`, `pay_delivery`, `Emp_Union`, `Salary`) VALUES " + 
		"(?,?,?,?,?,?,?,?);";
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, employee.getId());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getAddress());
			statement.setInt(5, employee.getPayType());
			statement.setInt(6, employee.getPayDelivery());
			statement.setString(7, employee.getUnion());
			statement.setInt(8, employee.getSalary());
			System.out.println(statement.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	public void deleteEmp(int id) {
		try {
			String sql = "DELETE FROM employees WHERE id=?";
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,id);
			System.out.println(statement.toString());
			statement.executeUpdate();
			
		}catch (SQLException e){
			System.out.println(e);
		}
	}


	public void updateEmp(Employee employee) {
		try {
			String sql = "UPDATE employees SET first_name = ?, last_name = ?, Address = ?, pay_type = ?, pay_delivery = ?, `Emp_Union` = ?, Salary = ?" 
						+ " WHERE id = ?;";
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, employee.getFirstName());
			statement.setString(2, employee.getLastName());
			statement.setString(3, employee.getAddress());
			statement.setInt(4, employee.getPayType());
			statement.setInt(5, employee.getPayDelivery());
			statement.setString(6, employee.getUnion());
			statement.setInt(7, employee.getSalary());
			statement.setInt(8, employee.getId());
			System.out.println(statement.toString());
			statement.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e);
		}
		
	}
}
