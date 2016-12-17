package netgloo.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="workingShift")
public class WorkingShift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long workingShiftID;
	
	@JsonBackReference("restaurant-workingShift")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurant;
	
	@JsonBackReference("employee-workingShift")
	@ManyToOne
	@JoinColumn(name="worker", referencedColumnName="employeeId", nullable=false)
	private Employee worker;
	
	private Timestamp shiftBeginningTime;
	
	private Timestamp shiftEndTime;
	
	private String note;


	

	public WorkingShift(Long workingShiftID, Restaurant restaurant, Employee worker, Timestamp shiftBeginningTime,
			Timestamp shiftEndTime, String note) {
		super();
		this.workingShiftID = workingShiftID;
		this.restaurant = restaurant;
		this.worker = worker;
		this.shiftBeginningTime = shiftBeginningTime;
		this.shiftEndTime = shiftEndTime;
		this.note = note;
	}
	
	

	public WorkingShift() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getWorkingShiftID() {
		return workingShiftID;
	}

	public void setWorkingShiftID(Long workingShiftID) {
		this.workingShiftID = workingShiftID;
	}

	public Employee getWorker() {
		return worker;
	}

	public void setWorker(Employee worker) {
		this.worker = worker;
	}

	public Timestamp getShiftBeginningTime() {
		return shiftBeginningTime;
	}

	public void setShiftBeginningTime(Timestamp shiftBeginningTime) {
		this.shiftBeginningTime = shiftBeginningTime;
	}

	public Timestamp getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(Timestamp shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	

}
