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
	@JoinColumn(name="restaurant_id", referencedColumnName="restaurant_id", nullable=false)
	private Restaurant restaurant_id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long beverages_id;
	
	@NotNull
	private String beverages_description;
	
	@NotNull
	private String beverages_name;
	
	@NotNull
	private Float beverages_price;

	public Beverages(Restaurant restaurant_id, Long beverages_id, String beverages_description, String beverages_name,
			Float beverages_price) {
		super();
		this.restaurant_id = restaurant_id;
		this.beverages_id = beverages_id;
		this.beverages_description = beverages_description;
		this.beverages_name = beverages_name;
		this.beverages_price = beverages_price;
	}

	public Beverages() {
		super();
	}

	public Restaurant getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Restaurant restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Long getBeverages_id() {
		return beverages_id;
	}

	public void setBeverages_id(Long beverages_id) {
		this.beverages_id = beverages_id;
	}

	public String getBeverages_description() {
		return beverages_description;
	}

	public void setBeverages_description(String beverages_description) {
		this.beverages_description = beverages_description;
	}

	public String getBeverages_name() {
		return beverages_name;
	}

	public void setBeverages_name(String beverages_name) {
		this.beverages_name = beverages_name;
	}

	public Float getBeverages_price() {
		return beverages_price;
	}

	public void setBeverages_price(Float beverages_price) {
		this.beverages_price = beverages_price;
	}


	
	
	
}
