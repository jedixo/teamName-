package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controll.Database;
import controll.EmpList;
import controll.Employee;
import controll.MD5;

@SuppressWarnings("serial")
public class AddEmployee extends JDialog {
	private JTextField firstName, lastName, address, union;
	private JPasswordField password;
	private JSpinner salary, commisionRate;
	private JComboBox<String> payType, payDelivery, level;
	private JLabel addLabel = new JLabel("    Rate:");
	private List<String> userLevels = Arrays.asList("Normal", "Administrator");
	private List<String> payTypes = Arrays.asList("Hourly rate", "Monthly salary", "Comission");
	private List<String> deliveryTypes = Arrays.asList("mail", "held", "direct deposite");

	public AddEmployee(final EmpList empList, final Database empDatabase) {
		final JPanel addPanel = new JPanel();
		addPanel.setLayout(new GridLayout(0,2,2,2));
		addPanel.add(new JLabel("    First Name:"));
		firstName = new JTextField();
		addPanel.add(firstName);
		addPanel.add(new JLabel("    Last Name:"));
		lastName = new JTextField();
		addPanel.add(lastName);
		addPanel.add(new JLabel("    Address:"));
		address = new JTextField();
		addPanel.add(address);
		addPanel.add(new JLabel("    Pay Type:"));
		payType = new JComboBox<String>();
		for (String type : payTypes) {
			payType.addItem(type);
		}
		addPanel.add(payType);
		
		payType.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().toString().equals("Comission")) {
					commisionRate(true);
					hourly(false);
					salary(true);
				}else if (e.getItem().toString().equals("Hourly rate")) {
					commisionRate(false);
					salary(false);
					hourly(true);
				}else {
					commisionRate(false);
					hourly(false);
					salary(true);
				}
			}
		});
		
		addPanel.add(new JLabel("    Pay Delivery:"));
		payDelivery = new JComboBox<String>();
		for (String delivery : deliveryTypes) {
			payDelivery.addItem(delivery);
		}
		addPanel.add(payDelivery);
		addPanel.add(new JLabel("    Union:"));
		union = new JTextField();
		addPanel.add(union);
		addPanel.add(addLabel);
		salary = new JSpinner();
		salary.setModel(new SpinnerNumberModel(0,0,10000,1));
		addPanel.add(salary);
		addPanel.add(new JLabel("    Commision Rate:"));
		commisionRate = new JSpinner();
		commisionRate.setEnabled(false);
		commisionRate.setModel(new SpinnerNumberModel(0,0,100,1));
		addPanel.add(commisionRate);
		addPanel.add(new JLabel("    Password:"));
		password = new JPasswordField();
		addPanel.add(password);
		addPanel.add(new JLabel("    User Level:"));
		level = new JComboBox<String>();
		for (String userLevel : userLevels) {
			level.addItem(userLevel);
		}
		addPanel.add(level);
		Button addButton = new Button("add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (hasValidInputs()) {
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
					employee.setCommissionRate((int) commisionRate.getValue());
					employee.setId(empList.get(empList.size() - 1).getId() + 1);
					employee.setPassword(MD5.hash(String.valueOf(password.getPassword())));
					employee.setUserLevel(level.getSelectedIndex());
					empDatabase.addEmpData(employee);
					if (!empDatabase.error) {
						empList.add(employee);
					}
				
					close();
				}
			}

			private boolean hasValidInputs() {
					if (firstName.getText().equals("") || String.valueOf(password.getPassword()).equals("") || lastName.getText().equals("") || firstName.getText().equals(lastName.getText()) || salary.getValue().equals(0)){
						JOptionPane.showMessageDialog(null, "Invalid Input please fill out the required fields", "Input Errr", JOptionPane.OK_OPTION);
						addPanel.getComponent(0).setForeground(Color.RED);
						addPanel.getComponent(2).setForeground(Color.RED);
						addPanel.getComponent(12).setForeground(Color.RED);
						addPanel.getComponent(16).setForeground(Color.RED);
						if (payType.getSelectedItem().equals("Comission") && commisionRate.getValue().equals(0)) {
							addPanel.getComponent(14).setForeground(Color.RED);
						}else {
							addPanel.getComponent(14).setForeground(Color.BLACK);
						}
						return false;
					} else {
						return true;
					}
			}
	
		});
		Button closeButton = new Button("cancel");
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
	
	private void commisionRate(boolean isActive) {
		commisionRate.setEnabled(isActive);

	}
	
	private void salary(boolean b) {
		salary.setEnabled(b);
		if(b) {
			addLabel.setText("    Salary:");
		}
	}
	
	private void hourly(boolean b) {
		salary.setEnabled(b);
		if(b) {
			addLabel.setText("    Rate:");
		}
	}
	
	private void close() {
		this.dispose();
		
	}
}
