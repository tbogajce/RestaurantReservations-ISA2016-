package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "area")
public class Area {

	@Id
	private Long areaID;
	
	@JsonBackReference("restaurant-area")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurant;
	
	@NotNull
	private String areaName;
	
	@NotNull
	private String areaSpace;
	
	private String note;

	public Area(Long areaID, Restaurant restaurant, String areaName, String areaSpace, String note) {
		super();
		this.areaID = areaID;
		this.restaurant = restaurant;
		this.areaName = areaName;
		this.areaSpace = areaSpace;
		this.note = note;
	}
	
	public Area()
	{
		super();
	}

	public Long getAreaID() {
		return areaID;
	}
	
	

	public void setAreaID(Long areaID) {
		this.areaID = areaID;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaSpace() {
		return areaSpace;
	}

	public void setAreaSpace(String areaSpace) {
		this.areaSpace = areaSpace;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
