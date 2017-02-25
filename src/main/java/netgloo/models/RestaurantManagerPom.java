package netgloo.models;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class RestaurantManagerPom {
	
	private String restaurantManagerNickId;
	
	private String restaurantManagerMail;
	
	private String restaurantManagerName;
	
	private String restaurantManagerSurname;
	
	private String restaurantManagerPassword;
	
	private String restaurantId;

	public RestaurantManagerPom(String restaurantManagerNickId, String restaurantManagerMail,
			String restaurantManagerName, String restaurantManagerSurname, String restaurantManagerPassword,
			String restaurantId) {
		super();
		this.restaurantManagerNickId = restaurantManagerNickId;
		this.restaurantManagerMail = restaurantManagerMail;
		this.restaurantManagerName = restaurantManagerName;
		this.restaurantManagerSurname = restaurantManagerSurname;
		this.restaurantManagerPassword = restaurantManagerPassword;
		this.restaurantId = restaurantId;
	}

	public RestaurantManagerPom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRestaurantManagerNickId() {
		return restaurantManagerNickId;
	}

	public void setRestaurantManagerNickId(String restaurantManagerNickId) {
		this.restaurantManagerNickId = restaurantManagerNickId;
	}

	public String getRestaurantManagerMail() {
		return restaurantManagerMail;
	}

	public void setRestaurantManagerMail(String restaurantManagerMail) {
		this.restaurantManagerMail = restaurantManagerMail;
	}

	public String getRestaurantManagerName() {
		return restaurantManagerName;
	}

	public void setRestaurantManagerName(String restaurantManagerName) {
		this.restaurantManagerName = restaurantManagerName;
	}

	public String getRestaurantManagerSurname() {
		return restaurantManagerSurname;
	}

	public void setRestaurantManagerSurname(String restaurantManagerSurname) {
		this.restaurantManagerSurname = restaurantManagerSurname;
	}

	public String getRestaurantManagerPassword() {
		return restaurantManagerPassword;
	}

	public void setRestaurantManagerPassword(String restaurantManagerPassword) {
		this.restaurantManagerPassword = restaurantManagerPassword;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	
	
	
}
