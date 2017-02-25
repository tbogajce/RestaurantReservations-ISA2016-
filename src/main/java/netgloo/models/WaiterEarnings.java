package netgloo.models;

import java.sql.Date;

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
@Table(name="waiterEarnings")
public class WaiterEarnings {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long waiterEarningId;
	
	@JsonBackReference("bill-waiterEarnings")
	@ManyToOne
	@JoinColumn(name="bill", referencedColumnName="billId", nullable=false)
	private Bill bill;
	
	@Column(nullable=true)
	private Date dateOfEarning;
	
	@Column(nullable=true)
	private float earned;
	
	@JsonBackReference("restaurant-waiterEarnings")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurant;
	
	@JsonBackReference("employee-waiterEarnings")
	@ManyToOne
	@JoinColumn(name="waiter", referencedColumnName="employeeId", nullable=true)
	private Employee waiter;

	public Long getWaiterEarningId() {
		return waiterEarningId;
	}

	public void setWaiterEarningId(Long waiterEarningId) {
		this.waiterEarningId = waiterEarningId;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Date getDateOfEarning() {
		return dateOfEarning;
	}

	public void setDateOfEarning(Date dateOfEarning) {
		this.dateOfEarning = dateOfEarning;
	}

	public float getEarned() {
		return earned;
	}

	public void setEarned(float earned) {
		this.earned = earned;
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

	public WaiterEarnings(Bill bill, Date dateOfEarning, float earned, Restaurant restaurant,
			Employee waiter) {
		super();
		this.bill = bill;
		this.dateOfEarning = dateOfEarning;
		this.earned = earned;
		this.restaurant = restaurant;
		this.waiter = waiter;
	}
	
	
	
	public WaiterEarnings()
	{
		super();
	}
	
	
}
