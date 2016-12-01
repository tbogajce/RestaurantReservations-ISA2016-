package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employee_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", table = "user")
	private Long user_id;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", table = "restaurant")
	private Long restaurant_id;
	
	@NotNull
	private String employee_role;

	@NotNull
	private String employee_confection_number;
	
	@NotNull
	private String employee_shoe_size;
	
	@NotNull
	private String employee_rate;

	public Employee(Long employee_id, Long user_id, Long restaurant_id, String employee_role,
			String employee_confection_number, String employee_shoe_size, String employee_rate) {
		super();
		this.employee_id = employee_id;
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
		this.employee_role = employee_role;
		this.employee_confection_number = employee_confection_number;
		this.employee_shoe_size = employee_shoe_size;
		this.employee_rate = employee_rate;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getEmployee_role() {
		return employee_role;
	}

	public void setEmployee_role(String employee_role) {
		this.employee_role = employee_role;
	}

	public String getEmployee_confection_number() {
		return employee_confection_number;
	}

	public void setEmployee_confection_number(String employee_confection_number) {
		this.employee_confection_number = employee_confection_number;
	}

	public String getEmployee_shoe_size() {
		return employee_shoe_size;
	}

	public void setEmployee_shoe_size(String employee_shoe_size) {
		this.employee_shoe_size = employee_shoe_size;
	}

	public String getEmployee_rate() {
		return employee_rate;
	}

	public void setEmployee_rate(String employee_rate) {
		this.employee_rate = employee_rate;
	}
	
	
	
}
