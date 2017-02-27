package netgloo.models;

public class EmployeePom {

	private Long employeeId;

	private String userId;

	private String restaurantId;

	private String employeeRole;

	private String employeeConfectionNumber;

	private String employeeShoeSize;

	private String employeeRate;

	private Boolean changedPass;

	public EmployeePom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeePom(Long employeeId, String userId, String restaurantId, String employeeRole,
			String employeeConfectionNumber, String employeeShoeSize, String employeeRate, Boolean changedPass) {
		super();
		this.employeeId = employeeId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.employeeRole = employeeRole;
		this.employeeConfectionNumber = employeeConfectionNumber;
		this.employeeShoeSize = employeeShoeSize;
		this.employeeRate = employeeRate;
		this.changedPass = changedPass;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public String getEmployeeConfectionNumber() {
		return employeeConfectionNumber;
	}

	public void setEmployeeConfectionNumber(String employeeConfectionNumber) {
		this.employeeConfectionNumber = employeeConfectionNumber;
	}

	public String getEmployeeShoeSize() {
		return employeeShoeSize;
	}

	public void setEmployeeShoeSize(String employeeShoeSize) {
		this.employeeShoeSize = employeeShoeSize;
	}

	public String getEmployeeRate() {
		return employeeRate;
	}

	public void setEmployeeRate(String employeeRate) {
		this.employeeRate = employeeRate;
	}

	public Boolean getChangedPass() {
		return changedPass;
	}

	public void setChangedPass(Boolean changedPass) {
		this.changedPass = changedPass;
	}
	
	
	
	
	

}
