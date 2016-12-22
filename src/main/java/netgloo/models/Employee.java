package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;
	
	@JsonBackReference("user-employee")
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName="userId", nullable=false)
	private User userId;
	
	
	@JsonBackReference("restaurant-employee")
	@ManyToOne
	@JoinColumn(name="restaurantId", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurantId;
	
	@NotNull
	private String employeeRole;

	@NotNull
	private String employeeConfectionNumber;
	
	@NotNull
	private String employeeShoeSize;
	
	@NotNull
	private String employeeRate;

	public Employee(Long employee_id, User user_id, Restaurant restaurant_id, String employee_role,
			String employee_confection_number, String employee_shoe_size, String employee_rate) {
		super();
		this.employeeId = employee_id;
		this.userId = user_id;
		this.restaurantId = restaurant_id;
		this.employeeRole = employee_role;
		this.employeeConfectionNumber = employee_confection_number;
		this.employeeShoeSize = employee_shoe_size;
		this.employeeRate = employee_rate;
	}

	public Employee() {
		super();
	}
	
	

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Restaurant getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Restaurant restaurantId) {
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

	public Long getEmployee_id() {
		return employeeId;
	}

	public void setEmployee_id(Long employee_id) {
		this.employeeId = employee_id;
	}

	public User getUser_id() {
		return userId;
	}

	public void setUser_id(User user_id) {
		this.userId = user_id;
	}

	public Restaurant getRestaurant_id() {
		return restaurantId;
	}

	public void setRestaurant_id(Restaurant restaurant_id) {
		this.restaurantId = restaurant_id;
	}

	public String getEmployee_role() {
		return employeeRole;
	}

	public void setEmployee_role(String employee_role) {
		this.employeeRole = employee_role;
	}

	public String getEmployee_confection_number() {
		return employeeConfectionNumber;
	}

	public void setEmployee_confection_number(String employee_confection_number) {
		this.employeeConfectionNumber = employee_confection_number;
	}

	public String getEmployee_shoe_size() {
		return employeeShoeSize;
	}

	public void setEmployee_shoe_size(String employee_shoe_size) {
		this.employeeShoeSize = employee_shoe_size;
	}

	public String getEmployee_rate() {
		return employeeRate;
	}

	public void setEmployee_rate(String employee_rate) {
		this.employeeRate = employee_rate;
	}

	
	
}
