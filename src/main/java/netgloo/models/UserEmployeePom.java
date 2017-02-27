package netgloo.models;

public class UserEmployeePom {

	private String email;
	private String password;
	private String name;
	private String surname;
	private String birthDate;

	private String employeeRole;
	private String employeeConfectionNumber;
	private String employeeShoeSize;
	private String employeeRate;

	private String restaurantId;

	public UserEmployeePom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEmployeePom(String email, String password, String name, String surname, String birthDate,
			String employeeRole, String employeeConfectionNumber, String employeeShoeSize, String employeeRate,
			String restaurantId) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.employeeRole = employeeRole;
		this.employeeConfectionNumber = employeeConfectionNumber;
		this.employeeShoeSize = employeeShoeSize;
		this.employeeRate = employeeRate;
		this.restaurantId = restaurantId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

}
