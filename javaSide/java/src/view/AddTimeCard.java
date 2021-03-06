package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	

	public AddTimeCard(final TimeCardList tcList, final Database database,EmpList empList,final int type,final TimeCardList ogTcList) {
		this.empList = empList;
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2,2,2));
		panel.add(new JLabel("    Employee:"));
		employees = new JComboBox<>();
		for (Employee employee : empList) {
			String first = employee.getFirstName();
			String last = employee.getLastName();
			String name = first +" "+ last;
			employees.addItem(name);
		}
		if (empList.size() == 1) {
			employees.setEnabled(false);
		}
		panel.add(employees);
		panel.add(new JLabel("    Date:"));
		
		Calendar cal = Calendar.getInstance();
		Date dte = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
		try {
			dateString = format.format(dte);
			date = new JTextField(dateString);
			date.setEditable(false);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Error:", JOptionPane.ERROR_MESSAGE);
		}
		
		panel.add(date);
		panel.add(new JLabel("    Hours:"));
		hours = new JSpinner();
		hours.setModel(new SpinnerNumberModel(0,0,24,0.5));
		panel.add(hours);
		Button addButton = new Button("add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (hasValidInput()) {
					if (type == 1) {
						boolean hasPreventry = false;
						for (Timecard tc : ogTcList) {
							if (tc.getDate().equals(dateString) && getEmpId() == tc.getEmpId()){
								hasPreventry = true;
							}
						}
						if (hasPreventry) {
							JOptionPane.showMessageDialog(null, "You can only have one timecard for a day.", "Input Error", JOptionPane.OK_OPTION);
						}else {
							Timecard tc = new Timecard(((ogTcList.get(ogTcList.size()-1).getId() + 1)),getEmpId(),dateString,(float)((double)hours.getValue()));
							ogTcList.add(tc);
							database.addTimeCard(tc);
							if (tcList.size() != 0) {
								tc = new Timecard(((tcList.get(tcList.size()-1).getId() + 1)),getEmpId(),dateString,(float)((double)hours.getValue()));
							} else
								tc = new Timecard(1,getEmpId(),dateString,(float)((double)hours.getValue()));
							tcList.add(tc);
						}
					} else {
						boolean hasPrevEntry = false;
						for (Timecard tc : tcList) {
							if (tc.getDate().equals(dateString)&& getEmpId() == tc.getEmpId()) {
								hasPrevEntry = true;
							}
						} 
						if (hasPrevEntry) {
							JOptionPane.showMessageDialog(null, "You can only have one timecard for a day.", "Input Error", JOptionPane.OK_OPTION);
						} else {
							Timecard tc = new Timecard(getEmpId(),dateString,(float)((double)hours.getValue()));
							database.addTimeCard(tc);
							tcList.add(tc);
						}
					}
					close();
				}
			}

			private boolean hasValidInput() {
				if (hours.getValue().equals(0.0)) {
					JOptionPane.showMessageDialog(null, "Enter the nubmer of hours worked.", "Input Error", JOptionPane.OK_OPTION);
					panel.getComponent(4).setForeground(Color.RED);
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
