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
@Table(name="bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long billId;
	
	@JsonBackReference("guestsOrder-bill")
	@ManyToOne
	@JoinColumn(name="guestsOrder", referencedColumnName="orderID", nullable=false)
	private GuestsOrder guestsOrder;
	
	@Column(nullable=true)
	private float totalSum;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public GuestsOrder getGuestsOrder() {
		return guestsOrder;
	}

	public void setGuestsOrder(GuestsOrder guestsOrder) {
		this.guestsOrder = guestsOrder;
	}

	public float getTotalSum() {
		return totalSum;
	}
	
	

	public Bill(GuestsOrder guestsOrder, float totalSum) {
		super();
		this.guestsOrder = guestsOrder;
		this.totalSum = totalSum;
	}

	public void setTotalSum(float totalSum) {
		this.totalSum = totalSum;
	}

	public Bill(GuestsOrder guestsOrder) {
		super();
		this.guestsOrder = guestsOrder;
	}
	
	public Bill()
	{
		super();
	}
	
	
	
}
