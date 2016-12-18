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
	private Float restaurantIncome;

	public Restaurant(Long restaurantId, String restaurantName, String restaurantType, String restaurantCoordinates,
			String restaurantAdress, Float restaurantRate, Integer restaurantVisitsNumber, Float restaurantIncome) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantType = restaurantType;
		this.restaurantCoordinates = restaurantCoordinates;
		this.restaurantAdress = restaurantAdress;
		this.restaurantRate = restaurantRate;
		this.restaurantVisitsNumber = restaurantVisitsNumber;
		this.restaurantIncome = restaurantIncome;
	}

	public Restaurant(String restaurantName, String restaurantType, String restaurantCoordinates,
			String restaurantAdress, Float restaurantRate, Integer restaurantVisitsNumber, Float restaurantIncome) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantType = restaurantType;
		this.restaurantCoordinates = restaurantCoordinates;
		this.restaurantAdress = restaurantAdress;
		this.restaurantRate = restaurantRate;
		this.restaurantVisitsNumber = restaurantVisitsNumber;
		this.restaurantIncome = restaurantIncome;
	}

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
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

	public Float getRestaurantIncome() {
		return restaurantIncome;
	}

	public void setRestaurantIncome(Float restaurantIncome) {
		this.restaurantIncome = restaurantIncome;
	}

}