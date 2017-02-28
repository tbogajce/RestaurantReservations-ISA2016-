package netgloo.models;

import javax.persistence.Column;
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
@Table(name = "area")
public class Area {

	@Id
	private Long areaID;

	@JsonBackReference("restaurant-area")
	@ManyToOne
	@JoinColumn(name = "restaurant", referencedColumnName = "restaurantId", nullable = false)
	private Restaurant restaurant;

	@NotNull
	private String areaName;

	// DO NOT USE THIS ANYMORE
	@NotNull
	private String areaSpace;

	// s lijeva na desno koliko celija
	@Column(name = "spaceX", nullable = true)
	private int spaceX;

	// ozgo ka dole koliko celija
	@Column(name = "spaceY", nullable = true)
	private int spaceY;
	
	@NotNull
	private String note;

	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Area(Long areaID, Restaurant restaurant, String areaName, String areaSpace, int spaceX, int spaceY,
			String note) {
		super();
		this.areaID = areaID;
		this.restaurant = restaurant;
		this.areaName = areaName;
		this.areaSpace = areaSpace;
		this.spaceX = spaceX;
		this.spaceY = spaceY;
		this.note = note;
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

	public int getSpaceX() {
		return spaceX;
	}

	public void setSpaceX(int spaceX) {
		this.spaceX = spaceX;
	}

	public int getSpaceY() {
		return spaceY;
	}

	public void setSpaceY(int spaceY) {
		this.spaceY = spaceY;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}
