package netgloo.models;

public class PriceToPay {

	
	
	public int guestsOrderId;
	
	public float price;

	public int getGuestsOrderId() {
		return guestsOrderId;
	}

	public void setGuestsOrderId(int guestsOrderId) {
		this.guestsOrderId = guestsOrderId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public PriceToPay(int guestsOrderId, float price) {
		super();
		this.guestsOrderId = guestsOrderId;
		this.price = price;
	}

	public PriceToPay() {
		super();
	}
	
	
	
	
}
