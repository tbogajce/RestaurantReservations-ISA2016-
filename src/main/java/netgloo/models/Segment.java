package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "segment")
public class Segment {

	@Id
	private Long segmentID;
	
	@JsonBackReference("restaurant-segment")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurant;
	
	@JsonBackReference("area-segment")
	@ManyToOne
	@JoinColumn(name="area", referencedColumnName="areaID", nullable=false)
	private Area area;
	
	@NotNull
	private String segmentName;
	
	@NotNull
	private String segmentSpace;
	
	private String note;

	public Segment(Long segmentID, Restaurant restaurant, Area area, String segmentName, String segmentSpace,
			String note) {
		super();
		this.segmentID = segmentID;
		this.restaurant = restaurant;
		this.area = area;
		this.segmentName = segmentName;
		this.segmentSpace = segmentSpace;
		this.note = note;
	}

	public Long getSegmentID() {
		return segmentID;
	}

	public void setSegmentID(Long segmentID) {
		this.segmentID = segmentID;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getSegmentSpace() {
		return segmentSpace;
	}

	public void setSegmentSpace(String segmentSpace) {
		this.segmentSpace = segmentSpace;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	
}
