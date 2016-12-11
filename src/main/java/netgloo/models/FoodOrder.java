package netgloo.models;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "foodOrder")
public class FoodOrder {
	

	@JsonBackReference("restaurant-foodOrder")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurant_id", nullable=false)
	private Restaurant restaurant;
	
	@JsonBackReference("employee-foodOrder")
	@ManyToOne
	@JoinColumn(name="employee", referencedColumnName="employee_id", nullable=false)
	private Employee employee;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	@JsonBackReference("menu-foodOrder")
	@ManyToOne
	@JoinColumn(name="menuMeal", referencedColumnName="menu_meal_id", nullable=false)
	private Menu menuMeal;
	
	@NotNull
	private Boolean foodOrderAcceptedStatus;
	
	private Boolean preparationFinishedStatus;
	
	private Timestamp foodOrderRecievementTime;
	
	private Timestamp foodOrderPreparationFinishedTime;

	public FoodOrder(Restaurant restaurant, Employee employee, Long orderId, Menu menuMeal,
			Boolean foodOrderAcceptedStatus, Boolean preparationFinishedStatus, Timestamp foodOrderRecievementTime,
			Timestamp foodOrderPreparationFinishedTime) {
		super();
		this.restaurant = restaurant;
		this.employee = employee;
		this.orderId = orderId;
		this.menuMeal = menuMeal;
		this.foodOrderAcceptedStatus = foodOrderAcceptedStatus;
		this.preparationFinishedStatus = preparationFinishedStatus;
		this.foodOrderRecievementTime = foodOrderRecievementTime;
		this.foodOrderPreparationFinishedTime = foodOrderPreparationFinishedTime;
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

	public Menu getMenuMeal() {
		return menuMeal;
	}

	public void setMenuMeal(Menu menuMeal) {
		this.menuMeal = menuMeal;
	}

	public Boolean getFoodOrderAcceptedStatus() {
		return foodOrderAcceptedStatus;
	}

	public void setFoodOrderAcceptedStatus(Boolean foodOrderAcceptedStatus) {
		this.foodOrderAcceptedStatus = foodOrderAcceptedStatus;
	}

	public Boolean getPreparationFinishedStatus() {
		return preparationFinishedStatus;
	}

	public void setPreparationFinishedStatus(Boolean preparationFinishedStatus) {
		this.preparationFinishedStatus = preparationFinishedStatus;
	}

	public Timestamp getFoodOrderRecievementTime() {
		return foodOrderRecievementTime;
	}

	public void setFoodOrderRecievementTime(Timestamp foodOrderRecievementTime) {
		this.foodOrderRecievementTime = foodOrderRecievementTime;
	}

	public Timestamp getFoodOrderPreparationFinishedTime() {
		return foodOrderPreparationFinishedTime;
	}

	public void setFoodOrderPreparationFinishedTime(Timestamp foodOrderPreparationFinishedTime) {
		this.foodOrderPreparationFinishedTime = foodOrderPreparationFinishedTime;
	}

	
}
