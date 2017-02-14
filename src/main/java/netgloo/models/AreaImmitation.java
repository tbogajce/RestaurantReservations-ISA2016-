package netgloo.models;

public class AreaImmitation {

	
	public String areaName;
	public String restaurantName;
	public Long areaID;
	public Long restaurantID;
	
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public Long getAreaID() {
		return areaID;
	}
	public void setAreaID(Long areaID) {
		this.areaID = areaID;
	}
	public Long getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(Long restaurantID) {
		this.restaurantID = restaurantID;
	}
	public AreaImmitation(String areaName, String restaurantName, Long areaID, Long restaurantID) {
		super();
		this.areaName = areaName;
		this.restaurantName = restaurantName;
		this.areaID = areaID;
		this.restaurantID = restaurantID;
	}
	
	
	
	
	
	public AreaImmitation()
	{
		super();
	}
	
	
}
