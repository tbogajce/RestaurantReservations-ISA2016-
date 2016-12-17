package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurant")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long restaurantId;
	
	@NotNull
	private String restaurantName;
	
	@NotNull
	private String restaurantType;
	
	@NotNull
	private String restaurantCoordinates;
	
	@NotNull
	private String restaurantAdress;
	
	@NotNull
	private Float restaurantRate;
	
	@NotNull
	private Integer restaurantVisitsNumber;
	
	@NotNull
	private Float rastaurantIncome;

	public Restaurant(Long restaurant_id, String restaurant_name, String restaurant_type, String restaurant_coordinates,
			String restaurant_adress, Float restaurant_rate, Integer restaurant_visits_number,
			Float rastaurant_income) {
		super();
		this.restaurantId = restaurant_id;
		this.restaurantName = restaurant_name;
		this.restaurantType = restaurant_type;
		this.restaurantCoordinates = restaurant_coordinates;
		this.restaurantAdress = restaurant_adress;
		this.restaurantRate = restaurant_rate;
		this.restaurantVisitsNumber = restaurant_visits_number;
		this.rastaurantIncome = rastaurant_income;
	}

	
	
	public Long getRestaurantId() {
		return restaurantId;
	}



	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}



	public String getRestaurantName() {
		return restaurantName;
	}



	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}



	public String getRestaurantType() {
		return restaurantType;
	}



	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}



	public String getRestaurantCoordinates() {
		return restaurantCoordinates;
	}



	public void setRestaurantCoordinates(String restaurantCoordinates) {
		this.restaurantCoordinates = restaurantCoordinates;
	}



	public String getRestaurantAdress() {
		return restaurantAdress;
	}



	public void setRestaurantAdress(String restaurantAdress) {
		this.restaurantAdress = restaurantAdress;
	}



	public Float getRestaurantRate() {
		return restaurantRate;
	}



	public void setRestaurantRate(Float restaurantRate) {
		this.restaurantRate = restaurantRate;
	}



	public Integer getRestaurantVisitsNumber() {
		return restaurantVisitsNumber;
	}



	public void setRestaurantVisitsNumber(Integer restaurantVisitsNumber) {
		this.restaurantVisitsNumber = restaurantVisitsNumber;
	}



	public Float getRastaurantIncome() {
		return rastaurantIncome;
	}



	public void setRastaurantIncome(Float rastaurantIncome) {
		this.rastaurantIncome = rastaurantIncome;
	}



	public Long getRestaurant_id() {
		return restaurantId;
	}

	public void setRestaurant_id(Long restaurant_id) {
		this.restaurantId = restaurant_id;
	}

	public String getRestaurant_name() {
		return restaurantName;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurantName = restaurant_name;
	}

	public String getRestaurant_type() {
		return restaurantType;
	}

	public void setRestaurant_type(String restaurant_type) {
		this.restaurantType = restaurant_type;
	}

	public String getRestaurant_coordinates() {
		return restaurantCoordinates;
	}

	public void setRestaurant_coordinates(String restaurant_coordinates) {
		this.restaurantCoordinates = restaurant_coordinates;
	}

	public String getRestaurant_adress() {
		return restaurantAdress;
	}

	public void setRestaurant_adress(String restaurant_adress) {
		this.restaurantAdress = restaurant_adress;
	}

	public Float getRestaurant_rate() {
		return restaurantRate;
	}

	public void setRestaurant_rate(Float restaurant_rate) {
		this.restaurantRate = restaurant_rate;
	}

	public Integer getRestaurant_visits_number() {
		return restaurantVisitsNumber;
	}

	public void setRestaurant_visits_number(Integer restaurant_visits_number) {
		this.restaurantVisitsNumber = restaurant_visits_number;
	}

	public Float getRastaurant_income() {
		return rastaurantIncome;
	}

	public void setRastaurant_income(Float rastaurant_income) {
		this.rastaurantIncome = rastaurant_income;
	}

	
}
