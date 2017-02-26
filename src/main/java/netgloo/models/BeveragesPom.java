package netgloo.models;

public class BeveragesPom {

	private String restaurantId;
	private Long beveragesId;
	private String beveragesDescription;
	private String beveragesName;
	private Float beveragesPrice;

	public BeveragesPom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BeveragesPom(String restaurantId, Long beveragesId, String beveragesDescription, String beveragesName,
			Float beveragesPrice) {
		super();
		this.restaurantId = restaurantId;
		this.beveragesId = beveragesId;
		this.beveragesDescription = beveragesDescription;
		this.beveragesName = beveragesName;
		this.beveragesPrice = beveragesPrice;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getBeveragesId() {
		return beveragesId;
	}

	public void setBeveragesId(Long beveragesId) {
		this.beveragesId = beveragesId;
	}

	public String getBeveragesDescription() {
		return beveragesDescription;
	}

	public void setBeveragesDescription(String beveragesDescription) {
		this.beveragesDescription = beveragesDescription;
	}

	public String getBeveragesName() {
		return beveragesName;
	}

	public void setBeveragesName(String beveragesName) {
		this.beveragesName = beveragesName;
	}

	public Float getBeveragesPrice() {
		return beveragesPrice;
	}

	public void setBeveragesPrice(Float beveragesPrice) {
		this.beveragesPrice = beveragesPrice;
	}

}
