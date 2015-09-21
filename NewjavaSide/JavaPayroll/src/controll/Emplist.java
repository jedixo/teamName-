package controll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class EmpList extends ArrayList<Employee>{

	public EmpList(ResultSet table) {
		try {
			while (table.next()){
				Employee employee = new Employee(table.getString("first_name"), table.getString("last_name"));
				employee.setId(table.getInt("id"));
				employee.setAddress(table.getString("Address"));
				employee.setPayType(table.getInt("pay_type"));
				employee.setPayDelivery(table.getInt("pay_delivery"));
				employee.setUnion(table.getString("Emp_Union"));
				employee.setSalary(table.getInt("Salary"));
				add(employee);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}

