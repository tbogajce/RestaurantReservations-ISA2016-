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
@Table(name = "diningTable")
public class DiningTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long generalTableID;
	
	@JsonBackReference("restaurant-diningTable")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurant;
	
	@NotNull
	private Long tableNumberInRestaurant;
	
	private Integer numberOfSeats;
	
	@JsonBackReference("area-diningTable")
	@ManyToOne
	@JoinColumn(name="area", referencedColumnName="areaID", nullable=false)
	private Area area;
	
	@JsonBackReference("segment-diningTable")
	@ManyToOne
	@JoinColumn(name="segment", referencedColumnName="segmentID", nullable=false)
	private Segment segment;
	
	/*
	//OVDE CE PISATI POSLJEDNJI ORDER UKOLIKO JE STO ZAUZET
	@JsonBackReference("guestsOrder-diningTable")
	@ManyToOne
	@JoinColumn(name="guestsOrder", referencedColumnName="orderID", nullable=true)
	private GuestsOrder guestsOrder;
	*/
	
	@Column(nullable =true)
	private String note;
	
	
	private Boolean occupied;
	
	
	//s lijeva na desno
	@Column(name="positionX", nullable =true)
	private int positionX;
	
	
	//ozgo ka dole
	@Column(name = "positionY", nullable =true)
	private int positionY;


	

	
	
	

	
	
	public DiningTable(Long generalTableID, Restaurant restaurant, Long tableNumberInRestaurant, Integer numberOfSeats,
			Area area, Segment segment, String note, Boolean occupied, int positionX, int positionY/*, GuestsOrder guestsOrder*/) {
		super();
		this.generalTableID = generalTableID;
		this.restaurant = restaurant;
		this.tableNumberInRestaurant = tableNumberInRestaurant;
		this.numberOfSeats = numberOfSeats;
		this.area = area;
		this.segment = segment;
		this.note = note;
		this.occupied = occupied;
		this.positionX = positionX;
		this.positionY = positionY;
		/*this.guestsOrder = guestsOrder;*/
	}




	
	
/*
	public GuestsOrder getGuestsOrder() {
		return guestsOrder;
	}







	public void setGuestsOrder(GuestsOrder guestsOrder) {
		this.guestsOrder = guestsOrder;
	}
*/






	public int getPositionX() {
		return positionX;
	}





	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}





	public int getPositionY() {
		return positionY;
	}





	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}





	public DiningTable() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Boolean getOccupied() {
		return occupied;
	}



	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}



	public Long getGeneralTableID() {
		return generalTableID;
	}

	public void setGeneralTableID(Long generalTableID) {
		this.generalTableID = generalTableID;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Long getTableNumberInRestaurant() {
		return tableNumberInRestaurant;
	}

	public void setTableNumberInRestaurant(Long tableNumberInRestaurant) {
		this.tableNumberInRestaurant = tableNumberInRestaurant;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
