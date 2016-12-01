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
@Table(name = "menu")
public class Menu {
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", table = "restaurant")
	private Long restaurant_id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long menu_meal_id;
	
	@NotNull
	private String menu_meal_description;
	
	@NotNull
	private Float menu_meal_price;
	
	@NotNull
	private Float menu_meal_rate;

	public Menu(Long restaurant_id, Long menu_meal_id, String menu_meal_description, Float menu_meal_price,
			Float menu_meal_rate) {
		super();
		this.restaurant_id = restaurant_id;
		this.menu_meal_id = menu_meal_id;
		this.menu_meal_description = menu_meal_description;
		this.menu_meal_price = menu_meal_price;
		this.menu_meal_rate = menu_meal_rate;
	}

	public Long getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Long getMenu_meal_id() {
		return menu_meal_id;
	}

	public void setMenu_meal_id(Long menu_meal_id) {
		this.menu_meal_id = menu_meal_id;
	}

	public String getMenu_meal_description() {
		return menu_meal_description;
	}

	public void setMenu_meal_description(String menu_meal_description) {
		this.menu_meal_description = menu_meal_description;
	}

	public Float getMenu_meal_price() {
		return menu_meal_price;
	}

	public void setMenu_meal_price(Float menu_meal_price) {
		this.menu_meal_price = menu_meal_price;
	}

	public Float getMenu_meal_rate() {
		return menu_meal_rate;
	}

	public void setMenu_meal_rate(Float menu_meal_rate) {
		this.menu_meal_rate = menu_meal_rate;
	}
	
	
}
