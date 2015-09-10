package controll;


import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ViewEmployees extends JFrame{
	
	public ViewEmployees(ArrayList<Employee> empList) {
		
		JPanel empPanel = new JPanel();
		empPanel.setLayout(new GridLayout(0,8,20,5));
		empPanel.add(new JLabel("id:"));
		empPanel.add(new JLabel("First Name:"));
		empPanel.add(new JLabel("Last Name:"));
		empPanel.add(new JLabel("Address:"));
		empPanel.add(new JLabel("pay Type:"));
		empPanel.add(new JLabel("Pay Delivery:"));
		empPanel.add(new JLabel("Union:"));
		empPanel.add(new JLabel("Salary:"));
		for (Employee employee : empList) {
			empPanel.add(new JLabel(String.valueOf(employee.getId())));
			empPanel.add(new JLabel(employee.getFirstName()));
			empPanel.add(new JLabel(employee.getLastName()));
			empPanel.add(new JLabel(employee.getAddress()));
			empPanel.add(new JLabel(String.valueOf(employee.getPayType())));
			empPanel.add(new JLabel(String.valueOf(employee.getPayDelivery())));
			empPanel.add(new JLabel(employee.getUnion()));
			empPanel.add(new JLabel(String.valueOf(employee.getSalary())));
		}
		this.add(empPanel);

		//setPreferredSize(new Dimension(260, 160));
		setVisible(true);
		pack();
		setTitle("employees");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

}