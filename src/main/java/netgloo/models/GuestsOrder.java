package netgloo.models;

import java.sql.Timestamp;

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
@Table(name = "guestsOrder")
public class GuestsOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderID;

	@JsonBackReference("restaurant-guestsOrder")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurant;
	
	@JsonBackReference("employee-guestsOrder")
	@ManyToOne
	@JoinColumn(name="waiter", referencedColumnName="employeeId", nullable=true)
	private Employee waiter;
	
	@JsonBackReference("user-guestsOrder")
	@ManyToOne
	@JoinColumn(name="guest", referencedColumnName="userId", nullable=true)
	private User guest;
	
	@JsonBackReference("diningTable-guestsOrder")
	@ManyToOne
	@JoinColumn(name="diningTable", referencedColumnName="generalTableID", nullable=false)
	private DiningTable diningTable;

	@Column(nullable=true)
	private Boolean IsPaid;
	
	
	@JsonBackReference("tableReservation-guestsOrder")
	@ManyToOne
	@JoinColumn(name="tableReservation", referencedColumnName="tableReservationId", nullable=true)
	private TableReservation tableReservation;
	
	
	private Timestamp orderReceivedTime;

	public GuestsOrder(Integer orderID, Restaurant restaurant, Employee waiter, User guest, DiningTable diningTable,
			Boolean isPaid, Timestamp orderReceivedTime) {
		super();
		this.orderID = orderID;
		this.restaurant = restaurant;
		this.waiter = waiter;
		this.guest = guest;
		this.diningTable = diningTable;
		this.IsPaid = isPaid;
		this.orderReceivedTime = orderReceivedTime;
	}
	
	public GuestsOrder(Restaurant restaurant, Employee waiter/*, User guest*/, DiningTable diningTable,
			Boolean isPaid, Timestamp orderReceivedTime) {
		super();
		//this.orderID = orderID;
		this.restaurant = restaurant;
		this.waiter = waiter;
		//this.guest = guest;
		this.diningTable = diningTable;
		this.IsPaid = isPaid;
		this.orderReceivedTime = orderReceivedTime;
	}
	

	public GuestsOrder(Restaurant restaurant, Employee waiter, User guest, DiningTable diningTable, Boolean isPaid,
			TableReservation tableReservation, Timestamp orderReceivedTime) {
		super();
		this.restaurant = restaurant;
		this.waiter = waiter;
		this.guest = guest;
		this.diningTable = diningTable;
		IsPaid = isPaid;
		this.tableReservation = tableReservation;
		this.orderReceivedTime = orderReceivedTime;
	}

	public GuestsOrder() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Integer getOrderID() {
		return orderID;
	}
	
	

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Employee getWaiter() {
		return waiter;
	}

	public void setWaiter(Employee waiter) {
		this.waiter = waiter;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public DiningTable getDiningTable() {
		return diningTable;
	}

	public void setDiningTable(DiningTable diningTable) {
		this.diningTable = diningTable;
	}

	public Boolean getIsPaid() {
		return IsPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		IsPaid = isPaid;
	}

	public Timestamp getOrderReceivedTime() {
		return orderReceivedTime;
	}

	public void setOrderReceivedTime(Timestamp orderReceivedTime) {
		this.orderReceivedTime = orderReceivedTime;
	}

	public TableReservation getTableReservation() {
		return tableReservation;
	}

	public void setTableReservation(TableReservation tableReservation) {
		this.tableReservation = tableReservation;
	}
	
	

}