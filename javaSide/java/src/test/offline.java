package test;
import javax.swing.UIManager;

import view.ViewFrame;
import controll.Database;
import controll.EmpList;
import controll.Employee;
import controll.TimeCardList;
import controll.Timecard;


public class offline {

	private static Database database;
	private static EmpList empList;
	private static TimeCardList timeCardList;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){}
		
		database = new Database();
		empList = new EmpList();
		Employee test = new Employee("Samantha", "Barber");
		test.setAddress("123 jake st");
		test.setCommissionRate(10);
		test.setId(1);
		test.setPayDelivery(1);
		test.setPayType(1);
		test.setSalary(2000);
		test.setUnion("JAK");
		empList.add(test);
		timeCardList = new TimeCardList();
		Timecard test2 = new Timecard(1, 1,"2015-01-01" , 20);
		timeCardList.add(test2);
		new ViewFrame(empList, database, timeCardList, null);
	}

}
