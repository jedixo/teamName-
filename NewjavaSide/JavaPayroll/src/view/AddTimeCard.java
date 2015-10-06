package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;


import controll.Database;
import controll.EmpList;
import controll.Employee;
import controll.TimeCardList;
import controll.Timecard;

@SuppressWarnings("serial")
public class AddTimeCard extends JDialog{
	
	private JTextField date;
	private JSpinner hours;
	private JComboBox<String> employees;
	private EmpList empList;
	private String dateString;

	

	public AddTimeCard(final TimeCardList tcList, final Database database,EmpList empList) {
		this.empList = empList;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2,2,2));
		panel.add(new JLabel("Employee:"));
		employees = new JComboBox<>();
		for (Employee employee : empList) {
			String first = employee.getFirstName();
			String last = employee.getLastName();
			String name = first +" "+ last;
			employees.addItem(name);
		}
		panel.add(employees);
		panel.add(new JLabel("Date:"));
		
		Calendar cal = Calendar.getInstance();
		Date dte = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
		try {
			dateString = format.format(dte);
			date = new JTextField(dateString);
			date.setEditable(false);
		}catch (Exception e) {
			date = new JTextField(e.toString());
		}
		
		panel.add(date);
		panel.add(new JLabel("Hours:"));
		hours = new JSpinner();
		hours.setModel(new SpinnerNumberModel(0,0,24,0.5));
		panel.add(hours);
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Timecard tc = new Timecard(((tcList.get(tcList.size()-1).getId() + 1)),getEmpId(),dateString,(float)((double)hours.getValue()));
				database.addTimeCard(tc);
				tcList.add(tc);
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
		panel.add(addButton);
		panel.add(closeButton);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(panel);
		setLocationRelativeTo(null);
		setPreferredSize(new Dimension(400, 200));
		setResizable(false);
		setTitle("Add Timecard");
		pack();
		setModal(true);
		setVisible(true);
	}
	
	private int getEmpId(){
		int id = 0;
		String[] nameStr = employees.getSelectedItem().toString().split("\\s+");
		for (Employee emp : empList) {
			if (emp.getFirstName().equals(nameStr[0]) && emp.getLastName().equals(nameStr[1])) {
				id = emp.getId();
			}
		}
		return id;
	}

	private void close() {
		this.dispose();
		
	}

}
