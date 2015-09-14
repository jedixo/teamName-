package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controll.Database;
import controll.Employee;

public class DelEmployee extends JDialog{

	private JComboBox<String> employees;
	
	public DelEmployee(final ArrayList<Employee> empList, final Database empDatabase) {
	JPanel delPanel = new JPanel();
	delPanel.setLayout(new GridLayout(2,2,10,20));
	delPanel.add(new JLabel("Employee:"));
	employees = new JComboBox<>();
	for (Employee employee : empList) {
		String first = employee.getFirstName();
		String last = employee.getLastName();
		int id = employee.getId();
		String name = first +" "+ last;
		employees.addItem(name);
	}
	delPanel.add(employees);
	JButton okButton = new JButton("Delete");
	okButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] nameStr = (String[])((String) employees.getSelectedItem()).split("\\s+");
			for (int i = 0; i < empList.size(); ++i) {
				
				if (empList.get(i).getFirstName().equals(nameStr[0]) && empList.get(i).getLastName().equals(nameStr[1])) {
					int id = empList.get(i).getId();
					empList.remove(i);
					empDatabase.deleteEmp(id);
					close();
				}
			}
		}
	});
	delPanel.add(okButton);
	
	
	JButton closeButton = new JButton("Cancel");
	closeButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			close();
			
		}
	});
	delPanel.add(closeButton);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	add(delPanel);
	setLocationRelativeTo(null);
	setPreferredSize(new Dimension(400, 100));
	setTitle("Delete Employee");
	
	pack();
	setModal(true);
	setVisible(true);
		
	
	}

	private void close() {
		this.dispose();
		
	}
}