package netgloo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "offerManager")
public class OfferManager {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer offerManagerId;
	
	@JsonBackReference("restaurantManager-offerManager")
	@ManyToOne
	@JoinColumn(name="restaurantManagerNickId", referencedColumnName="restaurantManagerNickId", nullable=false)
	private RestaurantManager restaurantManagerNickId;
	
	@Column(nullable=false)
	private String note;
	
	@Column(nullable=false)
	private String deadline;

	public OfferManager() {
		super();
	}

	public OfferManager(RestaurantManager restaurantManagerNickId, String note, String deadline) {
		super();
		this.restaurantManagerNickId = restaurantManagerNickId;
		this.note = note;
		this.deadline = deadline;
	}

	public Integer getOfferManagerId() {
		return offerManagerId;
	}

	public void setOfferManagerId(Integer offerManagerId) {
		this.offerManagerId = offerManagerId;
	}

	public RestaurantManager getRestaurantManagerNickId() {
		return restaurantManagerNickId;
	}

	public void setRestaurantManagerNickId(RestaurantManager restaurantManagerNickId) {
		this.restaurantManagerNickId = restaurantManagerNickId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	
	
	
}
