
package controll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

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
	
	public boolean updatetable() {
		
			return false;
			
		
		
	}
}
