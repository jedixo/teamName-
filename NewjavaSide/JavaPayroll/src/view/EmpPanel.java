package view;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import controll.EmpList;
import controll.Employee;

@SuppressWarnings("serial")
public class EmpPanel extends JPanel{
	
	private List<String> payTypes = Arrays.asList("Hourly rate", "Monthly salary", "Comission");
	private List<String> deliveryTypes = Arrays.asList("mail", "held", "direct deposite");

	public EmpPanel(EmpList empList) {
		this.setLayout(new GridLayout(0,9));
		
		this.add(new MainLabel("ID:","BOLD"));
		this.add(new MainLabel("First Name:","BOLD"));
		this.add(new MainLabel("Last Name:","BOLD"));
		this.add(new MainLabel("Address:","BOLD"));
		this.add(new MainLabel("pay Type:","BOLD"));
		this.add(new MainLabel("Pay Delivery:","BOLD"));
		this.add(new MainLabel("Union:","BOLD"));
		this.add(new MainLabel("Salary:","BOLD"));
		this.add(new MainLabel("Commision Rate","BOLD"));
		for (Employee employee : empList) {
			this.add(new MainLabel(String.valueOf(employee.getId()),"BOLD"));
			this.add(new MainLabel(employee.getFirstName()));
			this.add(new MainLabel(employee.getLastName()));
			this.add(new MainLabel(employee.getAddress()));
			this.add(new MainLabel(getPayTypeString(employee.getPayType())));
			this.add(new MainLabel(getPayDelevieryTypeString(employee.getPayDelivery())));
			this.add(new MainLabel(employee.getUnion()));
			this.add(new MainLabel(String.valueOf(employee.getSalary())));
			this.add(new MainLabel(employee.getCommissionRateString()));
			
		}	
	
	}


	private String getPayDelevieryTypeString(int payDelivery) {
		return deliveryTypes.get(payDelivery);
	}


	private String getPayTypeString(int payType) {
		return payTypes.get(payType);
	}
	
	
	
	

}
