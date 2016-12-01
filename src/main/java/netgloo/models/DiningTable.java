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
@Table(name = "diningTable")
public class DiningTable {
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", table = "restaurant")
	private Long restaurant_id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long diningTable_id;
	
	@NotNull
	private String diningTable_segment;
	
	@NotNull
	private String diningTable_area;

	public DiningTable(Long restaurant_id, Long diningTable_id, String diningTable_segment, String diningTable_area) {
		super();
		this.restaurant_id = restaurant_id;
		this.diningTable_id = diningTable_id;
		this.diningTable_segment = diningTable_segment;
		this.diningTable_area = diningTable_area;
	}

	public Long getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Long getDiningTable_id() {
		return diningTable_id;
	}

	public void setDiningTable_id(Long diningTable_id) {
		this.diningTable_id = diningTable_id;
	}

	public String getDiningTable_segment() {
		return diningTable_segment;
	}

	public void setDiningTable_segment(String diningTable_segment) {
		this.diningTable_segment = diningTable_segment;
	}

	public String getDiningTable_area() {
		return diningTable_area;
	}

	public void setDiningTable_area(String diningTable_area) {
		this.diningTable_area = diningTable_area;
	}
	
	
}
