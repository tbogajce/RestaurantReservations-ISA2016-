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
@Table(name = "beverages")
public class Beverages {
	
	@JsonBackReference("restaurant-beverages")
	@ManyToOne
	@JoinColumn(name="restaurantId", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurantId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long beveragesId;
	
	@NotNull
	private String beveragesDescription;
	
	@NotNull
	private String beveragesName;
	
	@NotNull
	private Float beveragesPrice;

	public Beverages(Restaurant restaurant_id, Long beverages_id, String beverages_description, String beverages_name,
			Float beverages_price) {
		super();
		this.restaurantId = restaurant_id;
		this.beveragesId = beverages_id;
		this.beveragesDescription = beverages_description;
		this.beveragesName = beverages_name;
		this.beveragesPrice = beverages_price;
	}

	public Beverages() {
		super();
	}
	
	
	

	public Restaurant getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Restaurant restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getBeveragesId() {
		return beveragesId;
	}

	public void setBeveragesId(Long beveragesId) {
		this.beveragesId = beveragesId;
	}

	public String getBeveragesDescription() {
		return beveragesDescription;
	}

	public void setBeveragesDescription(String beveragesDescription) {
		this.beveragesDescription = beveragesDescription;
	}

	public String getBeveragesName() {
		return beveragesName;
	}

	public void setBeveragesName(String beveragesName) {
		this.beveragesName = beveragesName;
	}

	public Float getBeveragesPrice() {
		return beveragesPrice;
	}

	public void setBeveragesPrice(Float beveragesPrice) {
		this.beveragesPrice = beveragesPrice;
	}

	public Restaurant getRestaurant_id() {
		return restaurantId;
	}

	public void setRestaurant_id(Restaurant restaurant_id) {
		this.restaurantId = restaurant_id;
	}

	public Long getBeverages_id() {
		return beveragesId;
	}

	public void setBeverages_id(Long beverages_id) {
		this.beveragesId = beverages_id;
	}

	public String getBeverages_description() {
		return beveragesDescription;
	}

	public void setBeverages_description(String beverages_description) {
		this.beveragesDescription = beverages_description;
	}

	public String getBeverages_name() {
		return beveragesName;
	}

	public void setBeverages_name(String beverages_name) {
		this.beveragesName = beverages_name;
	}

	public Float getBeverages_price() {
		return beveragesPrice;
	}

	public void setBeverages_price(Float beverages_price) {
		this.beveragesPrice = beverages_price;
	}


	
	
	
}
