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
@Table(name = "menu")
public class Menu {
	
	@JsonBackReference("restaurant-menu")
	@ManyToOne
	@JoinColumn(name="restaurantId", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurantId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long menuMealId;
	
	@NotNull
	private String menuMealDescription;
	
	@NotNull
	private Float menuMealPrice;
	
	@NotNull
	private Float menuMealRate;

	public Menu(Restaurant restaurant_id, Long menu_meal_id, String menu_meal_description, Float menu_meal_price,
			Float menu_meal_rate) {
		super();
		this.restaurantId = restaurant_id;
		this.menuMealId = menu_meal_id;
		this.menuMealDescription = menu_meal_description;
		this.menuMealPrice = menu_meal_price;
		this.menuMealRate = menu_meal_rate;
	}

	public Menu() {
		super();
	}
	
	

	public Restaurant getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Restaurant restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getMenuMealId() {
		return menuMealId;
	}

	public void setMenuMealId(Long menuMealId) {
		this.menuMealId = menuMealId;
	}

	public String getMenuMealDescription() {
		return menuMealDescription;
	}

	public void setMenuMealDescription(String menuMealDescription) {
		this.menuMealDescription = menuMealDescription;
	}

	public Float getMenuMealPrice() {
		return menuMealPrice;
	}

	public void setMenuMealPrice(Float menuMealPrice) {
		this.menuMealPrice = menuMealPrice;
	}

	public Float getMenuMealRate() {
		return menuMealRate;
	}

	public void setMenuMealRate(Float menuMealRate) {
		this.menuMealRate = menuMealRate;
	}

	public Restaurant getRestaurant_id() {
		return restaurantId;
	}

	public void setRestaurant_id(Restaurant restaurant_id) {
		this.restaurantId = restaurant_id;
	}

	public Long getMenu_meal_id() {
		return menuMealId;
	}

	public void setMenu_meal_id(Long menu_meal_id) {
		this.menuMealId = menu_meal_id;
	}

	public String getMenu_meal_description() {
		return menuMealDescription;
	}

	public void setMenu_meal_description(String menu_meal_description) {
		this.menuMealDescription = menu_meal_description;
	}

	public Float getMenu_meal_price() {
		return menuMealPrice;
	}

	public void setMenu_meal_price(Float menu_meal_price) {
		this.menuMealPrice = menu_meal_price;
	}

	public Float getMenu_meal_rate() {
		return menuMealRate;
	}

	public void setMenu_meal_rate(Float menu_meal_rate) {
		this.menuMealRate = menu_meal_rate;
	}

	
	
	
}
