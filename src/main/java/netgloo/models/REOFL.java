package netgloo.models;

public class REOFL {
	
	private String restaurantName;
	private Float restaurantEarned;
	public REOFL() {
		super();
		// TODO Auto-generated constructor stub
	}
	public REOFL(String restaurantName, Float restaurantEarned) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantEarned = restaurantEarned;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public Float getRestaurantEarned() {
		return restaurantEarned;
	}
	public void setRestaurantEarned(Float restaurantEarned) {
		this.restaurantEarned = restaurantEarned;
	}
	
	
	
}
