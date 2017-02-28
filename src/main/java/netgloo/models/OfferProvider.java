package netgloo.models;

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
@Table(name = "offerProvider")
public class OfferProvider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer offerProviderId;
	
	@JsonBackReference("provider-offerProvider")
	@ManyToOne
	@JoinColumn(name="providerNickId", referencedColumnName="providerNickId", nullable=false)
	private Provider providerNickId;
	
	@JsonBackReference("offerManager-offerProvider")
	@ManyToOne
	@JoinColumn(name="offerManagerId", referencedColumnName="offerManagerId", nullable=false)
	private OfferManager offerManagerId;
	
	@Column(nullable=false)
	private String price;
	
	@Column(nullable=false)
	private String note;
	
	@Column(nullable=false)
	private Boolean isAccepted;

	public OfferProvider() {
		super();
	}

	public OfferProvider(Provider providerNickId, OfferManager offerManagerId, String price, String note,
			Boolean isAccepted) {
		super();
		this.providerNickId = providerNickId;
		this.offerManagerId = offerManagerId;
		this.price = price;
		this.note = note;
		this.isAccepted = isAccepted;
	}

	public Integer getOfferProviderId() {
		return offerProviderId;
	}

	public void setOfferProviderId(Integer offerProviderId) {
		this.offerProviderId = offerProviderId;
	}

	public Provider getProviderNickId() {
		return providerNickId;
	}

	public void setProviderNickId(Provider providerNickId) {
		this.providerNickId = providerNickId;
	}

	public OfferManager getOfferManagerId() {
		return offerManagerId;
	}

	public void setOfferManagerId(OfferManager offerManagerId) {
		this.offerManagerId = offerManagerId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	

}
