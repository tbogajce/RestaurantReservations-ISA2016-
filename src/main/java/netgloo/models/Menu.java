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
@Table(name = "menu")
public class Menu {

	@JsonBackReference("restaurant-menu")
	@ManyToOne
	@JoinColumn(name = "restaurantId", referencedColumnName = "restaurantId", nullable = false)
	private Restaurant restaurantId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long menuMealId;

	@NotNull
	private String menuMealDescription;

	@NotNull
	private Float menuMealPrice;

	@NotNull
	private Float menuMealRate;
	
	@Column(nullable=true)
	private int totalNumberOfVoters;
	
	@Column(nullable=true)
	private int sumOfVotes;
	
	
	

	public int getTotalNumberOfVoters() {
		return totalNumberOfVoters;
	}

	public void setTotalNumberOfVoters(int totalNumberOfVoters) {
		this.totalNumberOfVoters = totalNumberOfVoters;
	}

	public int getSumOfVotes() {
		return sumOfVotes;
	}

	public void setSumOfVotes(int sumOfVotes) {
		this.sumOfVotes = sumOfVotes;
	}

	public Menu(Restaurant restaurantId, Long menuMealId, String menuMealDescription, Float menuMealPrice,
			Float menuMealRate, int totalNumberOfVoters, int sumOfVotes) {
		super();
		this.restaurantId = restaurantId;
		this.menuMealId = menuMealId;
		this.menuMealDescription = menuMealDescription;
		this.menuMealPrice = menuMealPrice;
		this.menuMealRate = menuMealRate;
		this.totalNumberOfVoters=totalNumberOfVoters;
		this.sumOfVotes=sumOfVotes;
	}

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Restaurant getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Restaurant restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getMenuMealId() {
		return menuMealId;
	}

	public void setMenuMealId(Long menuMealId) {
		this.menuMealId = menuMealId;
	}

	public String getMenuMealDescription() {
		return menuMealDescription;
	}

	public void setMenuMealDescription(String menuMealDescription) {
		this.menuMealDescription = menuMealDescription;
	}

	public Float getMenuMealPrice() {
		return menuMealPrice;
	}

	public void setMenuMealPrice(Float menuMealPrice) {
		this.menuMealPrice = menuMealPrice;
	}

	public Float getMenuMealRate() {
		return menuMealRate;
	}

	public void setMenuMealRate(Float menuMealRate) {
		this.menuMealRate = menuMealRate;
	}

}
