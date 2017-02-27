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
@Table(name="historyRecord")
public class HistoryRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long historyRecordId;
	
	@JsonBackReference("restaurant-historyRecord")
	@ManyToOne
	@JoinColumn(name="restaurant", referencedColumnName="restaurantId", nullable=false)
	private Restaurant restaurant;
	
	@JsonBackReference("guestsOrder-historyRecord")
	@ManyToOne
	@JoinColumn(name="guestsOrder", referencedColumnName="orderID", nullable=false)
	private GuestsOrder guestsOrder;
	
	@JsonBackReference("user-historyRecord")
	@ManyToOne
	@JoinColumn(name="user", referencedColumnName="userId", nullable=false)
	private User user;
	
	@Column(nullable=true)
	private float restaurantGrade;
	
	@Column(nullable=true)
	private boolean hasAlreadyGradedRestaurant;
	
	@Column(nullable=true)
	private boolean hasAlreadyGradedService;
	
	@Column(nullable=true)
	private boolean hasAlreadyGradedMeal;
	
	@Column(nullable=true)
	private float serviceGrade;
	
	@Column(nullable=true)
	private float mealGrade;
	
	@Column(nullable=true)
	private Date dateOfVisit;

	public Long getHistoryRecordId() {
		return historyRecordId;
	}

	public void setHistoryRecordId(Long historyRecordId) {
		this.historyRecordId = historyRecordId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public GuestsOrder getGuestsOrder() {
		return guestsOrder;
	}

	public void setGuestsOrder(GuestsOrder guestsOrder) {
		this.guestsOrder = guestsOrder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getRestaurantGrade() {
		return restaurantGrade;
	}

	public void setRestaurantGrade(float restaurantGrade) {
		this.restaurantGrade = restaurantGrade;
	}

	public float getServiceGrade() {
		return serviceGrade;
	}

	public void setServiceGrade(float serviceGrade) {
		this.serviceGrade = serviceGrade;
	}

	public float getMealGrade() {
		return mealGrade;
	}

	public void setMealGrade(float mealGrade) {
		this.mealGrade = mealGrade;
	}

	public Date getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(Date dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public boolean getHasAlreadyGradedRestaurant() {
		return hasAlreadyGradedRestaurant;
	}

	public void setHasAlreadyGradedRestaurant(boolean hasAlreadyGradedRestaurant) {
		this.hasAlreadyGradedRestaurant = hasAlreadyGradedRestaurant;
	}

	public boolean getHasAlreadyGradedService() {
		return hasAlreadyGradedService;
	}

	public void setHasAlreadyGradedService(boolean hasAlreadyGradedService) {
		this.hasAlreadyGradedService = hasAlreadyGradedService;
	}

	public boolean getHasAlreadyGradedMeal() {
		return hasAlreadyGradedMeal;
	}

	public void setHasAlreadyGradedMeal(boolean hasAlreadyGradedMeal) {
		this.hasAlreadyGradedMeal = hasAlreadyGradedMeal;
	}

	public HistoryRecord(Restaurant restaurant, GuestsOrder guestsOrder, User user, Date dateOfVisit) {
		super();
		this.restaurant = restaurant;
		this.guestsOrder = guestsOrder;
		this.user = user;
		this.dateOfVisit = dateOfVisit;
		this.restaurantGrade=0;
		this.serviceGrade=0;
		this.mealGrade=0;
		this.hasAlreadyGradedMeal=false;
		this.hasAlreadyGradedRestaurant=false;
		this.hasAlreadyGradedService=false;
	}
	
	
	public HistoryRecord()
	{
		super();
	}

}
