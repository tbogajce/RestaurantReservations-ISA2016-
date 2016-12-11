package netgloo.models;

import java.sql.Timestamp;

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
@Table(name="drinkOrder")
public class DrinkOrder {


	@JsonBackReference("restaurant-drinkOrder")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurant_id", nullable=false)
	private Restaurant restaurant;
	//ili
	//private Restaurant restaurantId;
	
	@JsonBackReference("employee-drinkOrder")
	@ManyToOne
	@JoinColumn(name="employee", referencedColumnName="employee_id", nullable=false)
	private Employee employee;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	@JsonBackReference("beverages-drinkOrder")
	@ManyToOne
	@JoinColumn(name="beverage", referencedColumnName="beverages_id", nullable=false)
	private Beverages beverage;

	@NotNull
	private Boolean drinkOrderAcceptedStatus;
	
	private Boolean preparationFinishedStatus;
	
	private Timestamp drinkOrderRecievementTime;
	
	private Timestamp drinkOrderPreparationFinishedTime;

	public DrinkOrder(Restaurant restaurant, Employee employee, Long orderId, Beverages beverage,
			Boolean drinkOrderAcceptedStatus, Boolean preparationFinishedStatus, Timestamp drinkOrderRecievementTime,
			Timestamp drinkOrderPreparationFinishedTime) {
		super();
		this.restaurant = restaurant;
		this.employee = employee;
		this.orderId = orderId;
		this.beverage = beverage;
		this.drinkOrderAcceptedStatus = drinkOrderAcceptedStatus;
		this.preparationFinishedStatus = preparationFinishedStatus;
		this.drinkOrderRecievementTime = drinkOrderRecievementTime;
		this.drinkOrderPreparationFinishedTime = drinkOrderPreparationFinishedTime;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Beverages getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverages beverage) {
		this.beverage = beverage;
	}

	public Boolean getDrinkOrderAcceptedStatus() {
		return drinkOrderAcceptedStatus;
	}

	public void setDrinkOrderAcceptedStatus(Boolean drinkOrderAcceptedStatus) {
		this.drinkOrderAcceptedStatus = drinkOrderAcceptedStatus;
	}

	public Boolean getPreparationFinishedStatus() {
		return preparationFinishedStatus;
	}

	public void setPreparationFinishedStatus(Boolean preparationFinishedStatus) {
		this.preparationFinishedStatus = preparationFinishedStatus;
	}

	public Timestamp getDrinkOrderRecievementTime() {
		return drinkOrderRecievementTime;
	}

	public void setDrinkOrderRecievementTime(Timestamp drinkOrderRecievementTime) {
		this.drinkOrderRecievementTime = drinkOrderRecievementTime;
	}

	public Timestamp getDrinkOrderPreparationFinishedTime() {
		return drinkOrderPreparationFinishedTime;
	}

	public void setDrinkOrderPreparationFinishedTime(Timestamp drinkOrderPreparationFinishedTime) {
		this.drinkOrderPreparationFinishedTime = drinkOrderPreparationFinishedTime;
	}
	
	
	
}
