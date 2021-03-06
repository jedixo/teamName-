package controll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TimeCardList extends ArrayList<Timecard> {
	
	public TimeCardList() {}

	public TimeCardList(ResultSet table) {
		try {
			while (table.next()){
				Timecard tc = new Timecard(table.getInt("id"), table.getInt("employee"), table.getString("date"), table.getFloat("hours"));
				add(tc);
			}
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e,"Error:", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
