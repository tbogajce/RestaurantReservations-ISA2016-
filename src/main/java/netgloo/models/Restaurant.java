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
	private Long restaurant_id;
	
	@NotNull
	private String restaurant_name;
	
	@NotNull
	private String restaurant_type;
	
	@NotNull
	private String restaurant_coordinates;
	
	@NotNull
	private String restaurant_adress;
	
	@NotNull
	private Float restaurant_rate;
	
	@NotNull
	private Integer restaurant_visits_number;
	
	@NotNull
	private Float rastaurant_income;

	public Restaurant(Long restaurant_id, String restaurant_name, String restaurant_type, String restaurant_coordinates,
			String restaurant_adress, Float restaurant_rate, Integer restaurant_visits_number,
			Float rastaurant_income) {
		super();
		this.restaurant_id = restaurant_id;
		this.restaurant_name = restaurant_name;
		this.restaurant_type = restaurant_type;
		this.restaurant_coordinates = restaurant_coordinates;
		this.restaurant_adress = restaurant_adress;
		this.restaurant_rate = restaurant_rate;
		this.restaurant_visits_number = restaurant_visits_number;
		this.rastaurant_income = rastaurant_income;
	}

	public Long getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public String getRestaurant_type() {
		return restaurant_type;
	}

	public void setRestaurant_type(String restaurant_type) {
		this.restaurant_type = restaurant_type;
	}

	public String getRestaurant_coordinates() {
		return restaurant_coordinates;
	}

	public void setRestaurant_coordinates(String restaurant_coordinates) {
		this.restaurant_coordinates = restaurant_coordinates;
	}

	public String getRestaurant_adress() {
		return restaurant_adress;
	}

	public void setRestaurant_adress(String restaurant_adress) {
		this.restaurant_adress = restaurant_adress;
	}

	public Float getRestaurant_rate() {
		return restaurant_rate;
	}

	public void setRestaurant_rate(Float restaurant_rate) {
		this.restaurant_rate = restaurant_rate;
	}

	public Integer getRestaurant_visits_number() {
		return restaurant_visits_number;
	}

	public void setRestaurant_visits_number(Integer restaurant_visits_number) {
		this.restaurant_visits_number = restaurant_visits_number;
	}

	public Float getRastaurant_income() {
		return rastaurant_income;
	}

	public void setRastaurant_income(Float rastaurant_income) {
		this.rastaurant_income = rastaurant_income;
	}

	
}
