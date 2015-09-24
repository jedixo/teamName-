package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controll.Database;
import controll.EmpList;
import controll.TimeCardList;

@SuppressWarnings("serial")
public class ViewFrame extends JFrame{
	private EmpList empList;
	private TimeCardList tcList;
	private TcPanel tcPanel;
	private int type;
	private EmpPanel empPanel;
	private JButton addButton;
	private JButton modButton;
	private JScrollPane ScrollPane;
	private Database database;
	
	public ViewFrame(final EmpList empList, final Database database) {
		type = 0;
		this.database = database;
		this.empList = empList;
		empPanel = new EmpPanel(empList);
		ScrollPane = new JScrollPane(empPanel);
		add(ScrollPane, BorderLayout.CENTER);
		
		update();
		
	}

	public ViewFrame(TimeCardList timeCardList, Database database) {
		type = 1;
		this.database = database;
		this.tcList = timeCardList;
		tcPanel = new TcPanel(tcList);
		ScrollPane = new JScrollPane(tcPanel);
		add(ScrollPane, BorderLayout.CENTER);
		
		update();
	}

	private void update() {
		remove(ScrollPane);
		add(ScrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();

		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				if (type == 0) {
					new AddEmployee(empList, database);
				} else {
					new AddTimeCard(tcList, database);
				}
				update();	
			}
		});
		buttonPanel.add(addButton);
		if (type == 0) {
			modButton = new JButton("Modify");
			modButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {

					new ModEmployee(empList, database);
					update();	
				}
			});
			buttonPanel.add(modButton);
		}
		add(buttonPanel, BorderLayout.SOUTH);
		
		
		if (type == 0) {
			empPanel = new EmpPanel(empList);
			ScrollPane = new JScrollPane(empPanel);
		}
		
		
		add(ScrollPane, BorderLayout.CENTER);
		setPreferredSize(new Dimension(800, 600));
		setResizable(false);
		pack();
		setTitle("View Data");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
