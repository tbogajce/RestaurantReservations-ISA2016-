package netgloo.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class MenuPom {

	private String restaurantId;
	private Long menuMealId;
	private String menuMealDescription;
	private Float menuMealPrice;
	private Float menuMealRate;

	public MenuPom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuPom(String restaurantId, Long menuMealId, String menuMealDescription, Float menuMealPrice,
			Float menuMealRate) {
		super();
		this.restaurantId = restaurantId;
		this.menuMealId = menuMealId;
		this.menuMealDescription = menuMealDescription;
		this.menuMealPrice = menuMealPrice;
		this.menuMealRate = menuMealRate;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
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

}
