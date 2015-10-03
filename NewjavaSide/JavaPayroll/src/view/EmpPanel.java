package view;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import controll.EmpList;
import controll.Employee;
import controll.viewTable;

@SuppressWarnings("serial")
public class EmpPanel extends JPanel{
	
	private List<String> payTypes = Arrays.asList("Hourly rate", "Monthly salary", "Comission");
	private List<String> deliveryTypes = Arrays.asList("mail", "held", "direct deposite");
	private String[] columNames = {"Id:","First Name:","Last Name:","Address:",
			"Pay Type:","Pay Delivery:","Union:","Salary:","Commision Rate:"};
	
	public EmpPanel(EmpList empList) {
		
		viewTable table = new viewTable(columNames);
		
		for (Employee emp : empList) {
			Object[] temp = {emp.getId(),emp.firstName,emp.lastName,emp.getAddress(),getPayTypeString(emp.getPayType()),getPayDelevieryTypeString(emp.getPayDelivery())
					,emp.getUnion(),emp.getSalary(),emp.getCommissionRateString()};
			table.addRow(temp);
		}
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		
		add(table,BorderLayout.CENTER);
	}


	private String getPayDelevieryTypeString(int payDelivery) {
		return deliveryTypes.get(payDelivery);
	}


	private String getPayTypeString(int payType) {
		return payTypes.get(payType);
	}
	
	
	
	

}
