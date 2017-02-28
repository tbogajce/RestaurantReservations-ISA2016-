package netgloo.models;

public class OfferProviderPom {
	private String offerProviderId;
	private String note;
	private String price;
	public OfferProviderPom() {
		super();
	}
	public OfferProviderPom(String offerProviderId, String note, String price) {
		super();
		this.offerProviderId = offerProviderId;
		this.note = note;
		this.price = price;
	}
	public String getOfferProviderId() {
		return offerProviderId;
	}
	public void setOfferProviderId(String offerProviderId) {
		this.offerProviderId = offerProviderId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	

}
