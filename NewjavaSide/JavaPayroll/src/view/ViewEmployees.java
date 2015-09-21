package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import controll.Database;
import controll.EmpList;

@SuppressWarnings("serial")
public class ViewEmployees extends JFrame{
	private EmpList empList;
	private Database empDatabase;
	
	private EmpPanel empPanel;
	private JButton addButton;
	private JButton modButton;
	private JScrollPane ScrollPane;
	
	public ViewEmployees(EmpList empList, Database empDatabase) {
		this.empList = empList;
		this.empDatabase = empDatabase;
		
		empPanel = new EmpPanel(empList);
		ScrollPane = new JScrollPane(empPanel);
		addButton = new JButton("Add");
		modButton = new JButton("Modify");
		empPanel.add(addButton);
		empPanel.add(modButton);
		add(ScrollPane, BorderLayout.CENTER);
		update();
		
		setPreferredSize(new Dimension(905, 561));
		pack();
		setTitle("employees");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}

	private void update() {
		remove(ScrollPane);
		remove(addButton);
		remove(modButton);
		
		empPanel = new EmpPanel(empList);
		ScrollPane = new JScrollPane(empPanel);
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddEmployee(empList, empDatabase);
				update();	
			}
		});
		
		modButton = new JButton("Modify");
		modButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModEmployee(empList, empDatabase);
				update();	
			}
		});
		
		empPanel.add(addButton);
		empPanel.add(modButton);
		add(ScrollPane, BorderLayout.CENTER);
		setVisible(true);
		
	}

}
