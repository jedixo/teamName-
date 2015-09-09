
package controll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	
	private Connection connection = null;
	private String host = null;
	
	public Database(String host, String user, String password) {
		this.host = host;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://" + host, user, password);
			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ResultSet getEmployees() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
			return rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	
	}
	
	public String getHost() {
		return host;
	}

}
