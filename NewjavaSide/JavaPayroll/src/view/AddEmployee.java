package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controll.Database;
import controll.Employee;

@SuppressWarnings("serial")
public class AddEmployee extends JDialog {
	private JTextField firstName, lastName, address, union;
	private JSpinner salary;
	private JComboBox<String> payType, payDelivery;
	private List<String> payTypes = Arrays.asList("Hourly rate", "Monthly salary", "Comission");
	private List<String> deliveryTypes = Arrays.asList("mail", "held", "direct deposite");


	
	public AddEmployee(final ArrayList<Employee> empList, final Database empDatabase) {
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new GridLayout(0,2,2,2));
		addPanel.add(new JLabel("First Name:"));
		firstName = new JTextField();
		addPanel.add(firstName);
		addPanel.add(new JLabel("Last Name:"));
		lastName = new JTextField();
		addPanel.add(lastName);
		addPanel.add(new JLabel("Address:"));
		address = new JTextField();
		addPanel.add(address);
		addPanel.add(new JLabel("Pay Type:"));
		payType = new JComboBox<String>();
		for (String type : payTypes) {
			payType.addItem(type);
		}
		addPanel.add(payType);
		addPanel.add(new JLabel("Pay Delivery:"));
		payDelivery = new JComboBox<String>();
		for (String delivery : deliveryTypes) {
			payDelivery.addItem(delivery);
		}
		addPanel.add(payDelivery);
		addPanel.add(new JLabel("Union:"));
		union = new JTextField();
		addPanel.add(union);
		addPanel.add(new JLabel("Salary:"));
		salary = new JSpinner();
		salary.setModel(new SpinnerNumberModel(0,0,10000,1));
		addPanel.add(salary);
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee employee = new Employee(firstName.getText(), lastName.getText());
				employee.setAddress(address.getText());
				String type = (String) payType.getSelectedItem();
				for (int i = 0; i < payTypes.size(); i++) {
					if (payTypes.get(i).equals(type)) {
						employee.setPayType(i);
					}
				}
				type = (String) payDelivery.getSelectedItem();
				for (int i = 0; i < deliveryTypes.size(); i++) {
					if (deliveryTypes.get(i).equals(type)) {
						employee.setPayDelivery(i);
					}
				}
				employee.setUnion(union.getText());
				employee.setSalary((int) salary.getValue());
				employee.setId(empList.get(empList.size() - 1).getId() + 1);
				empList.add(employee);
				empDatabase.addEmpData(employee);
				
				close();
			}
	
		});
		JButton closeButton = new JButton("Cancel");
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				
			}
		});

		
		addPanel.add(addButton);
		addPanel.add(closeButton);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(addPanel);
		setLocationRelativeTo(null);
		setPreferredSize(new Dimension(400, 300));
		setTitle("Add Employee");
		setResizable(false);		
		pack();
		setModal(true);
		setVisible(true);
	}
	private void close() {
		this.dispose();
		
	}

}