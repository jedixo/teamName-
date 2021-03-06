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
import controll.SalesRecipt;
import controll.SalesRecipts;

@SuppressWarnings("serial")
public class AddSalesRecipt extends JDialog{
	
	private JTextField date;
	private JSpinner amount;
	private JComboBox<String> employees;
	private EmpList empList;
	private String dateString;

	
	public AddSalesRecipt(final SalesRecipts salesRecipts, final Database database,
			EmpList empList, final int type,final SalesRecipts ogSrList) {
		this.empList = empList;
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2,2,2));
		panel.add(new JLabel("    Employee:"));
		employees = new JComboBox<>();
		for (Employee emp : empList) {
			String first = emp.getFirstName();
			String last = emp.getLastName();
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
		panel.add(new JLabel("    Amount:"));
		amount = new JSpinner();
		amount.setModel(new SpinnerNumberModel(0,0,Float.MAX_VALUE,1));
		panel.add(amount);
		Button addButton = new Button("add");
addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (hasValidInputs()) {
					if (type == 1) {
						SalesRecipt sr = new SalesRecipt(((ogSrList.get(ogSrList.size()-1).getId() + 1)),getEmpId(),dateString,(float)((double)amount.getValue()));
						ogSrList.add(sr);
						database.addSalesRecipt(sr);
						if (salesRecipts.size() != 0) {
							sr = new SalesRecipt(((salesRecipts.get(salesRecipts.size()-1).getId() + 1)),getEmpId(),dateString,(float)((double)amount.getValue()));
						} else
							sr = new SalesRecipt(1,getEmpId(),dateString,(float)((double)amount.getValue()));
						salesRecipts.add(sr);
					} else {
						SalesRecipt sr = new SalesRecipt(getEmpId(),dateString,(float)((double)amount.getValue()));
						database.addSalesRecipt(sr);
						salesRecipts.add(sr);
					}
					close();
				}
			}
			
			private boolean hasValidInputs() {
				if (amount.getValue().equals(0.0)) {
					JOptionPane.showMessageDialog(null, "Enter the recipt amount.", "Input Error", JOptionPane.OK_OPTION);
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
		setTitle("Add Sale Recipt");
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
