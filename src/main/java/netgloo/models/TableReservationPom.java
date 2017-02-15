package netgloo.models;

public class TableReservationPom {
	public String restaurantId;
	public String date;
	public String time;
	public String hours;
	public String diningTableId;
	public TableReservationPom() {
		super();
	}
	public TableReservationPom(String restaurantId, String date, String time, String hours, String diningTableId) {
		super();
		this.restaurantId = restaurantId;
		this.date = date;
		this.time = time;
		this.hours = hours;
		this.diningTableId = diningTableId;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
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
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getDiningTableId() {
		return diningTableId;
	}
	public void setDiningTableId(String diningTableId) {
		this.diningTableId = diningTableId;
	}
	
	
}
