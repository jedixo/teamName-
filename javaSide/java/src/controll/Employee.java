package controll;


public class Employee{
	
	public int id = 0;
	public String firstName, lastName;
	private String address = null, union = null, password = null;
	private int payType = 0, payDelivery = 0, salary = 0, userLevel = 0;
	private float commissionRate = 0;


	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	public void setPassword(String md5) {
		if (password == null) {
			this.password = md5;
		}
	}
	
	public String getPasswordMD5(String Lock) {
		if (Lock.equals("lA7*wL7!")) {
			return password;
		} else {
			return null;
		}
	}
	
	public boolean matchPassword(String password) {
			String md5 = MD5.hash(password); 
			if (md5.equals(this.password)) {
				return true;
			} else {
				return false;
			}
	}
	
	public float getCommisionRateFloat() {
		return commissionRate;
	}
	
	
	public String getCommissionRateString() {
		return commissionRate + "%";
	}

	public void setCommissionRate(float commissionRate) {
		this.commissionRate = commissionRate;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUnion() {
		return union;
	}

	public void setUnion(String union) {
		this.union = union;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getPayDelivery() {
		return payDelivery;
	}

	public void setPayDelivery(int payDelivery) {
		this.payDelivery = payDelivery;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

}
