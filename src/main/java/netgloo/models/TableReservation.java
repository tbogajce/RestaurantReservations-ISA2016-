package netgloo.models;

import java.sql.Time;
import java.util.Date;

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
@Table(name = "tableReservation")
public class TableReservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tableReservationId;
	
	@JsonBackReference("restaurant-tableReservation")
	@ManyToOne
	@JoinColumn(name="restaurantId", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurantId;
	
	@JsonBackReference("user-tableReservation")
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName="userId", nullable=false)
	private User userId;
	
	@NotNull
	private String date;
	
	@NotNull
	private String time;
	
	@NotNull
	private Integer hours;
	
	@JsonBackReference("diningTable-tableReservation")
	@ManyToOne
	@JoinColumn(name="diningTableId", referencedColumnName="generalTableID", nullable=false)
	private DiningTable diningTableId;
	
	public TableReservation() {
		super();
	}
	public TableReservation(Restaurant restaurantId, String date, String time, Integer hours, DiningTable diningTable, User user) {
		super();
		this.restaurantId = restaurantId;
		this.date = date;
		this.time = time;
		this.hours = hours;
		this.diningTableId = diningTable;
		this.userId = user;
	}
	
	
	public TableReservation(Restaurant restaurantId, String date, String time, Integer hours,
			DiningTable diningTableId) {
		super();
		this.restaurantId = restaurantId;
		this.date = date;
		this.time = time;
		this.hours = hours;
		this.diningTableId = diningTableId;
	}
	public Integer getTableReservationId() {
		return tableReservationId;
	}
	public void setTableReservationId(Integer tableReservationId) {
		this.tableReservationId = tableReservationId;
	}
	public Restaurant getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Restaurant restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public DiningTable getDiningTableId() {
		return diningTableId;
	}
	public void setDiningTableId(DiningTable diningTableId) {
		this.diningTableId = diningTableId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}	
	
}
