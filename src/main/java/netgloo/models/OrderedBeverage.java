package netgloo.models;

import java.sql.Timestamp;

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
@Table(name="orderedBeverage")
public class OrderedBeverage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderedBeverageID;
	
	@JsonBackReference("guestsOrder-orderedBeverage")
	@ManyToOne
	@JoinColumn(name="guestsOrder", referencedColumnName="orderID", nullable=false)
	private GuestsOrder guestsOrder;
	
	
	@JsonBackReference("employee-orderedBeverage")
	@ManyToOne
	@JoinColumn(name="bartender", referencedColumnName="employee_id", nullable=true)
	private Employee bartender;
	
	@JsonBackReference("beverages-orderedBeverage")
	@ManyToOne
	@JoinColumn(name="beverage", referencedColumnName="beverages_id", nullable=false)
	private Beverages beverage;
	
	@NotNull
	private Integer quantity;
	
	@Column(nullable=true)
	private String orderedBeverageNote;
	
	//@Column(nullable=true)
	private Boolean IsAccepted;
	
	@Column(nullable=true)
	private Boolean IsDone;
	
	@Column(nullable=true)
	private Timestamp acceptedTime;
	
	@Column(nullable=true)
	private Timestamp doneTime;

	public OrderedBeverage(Integer orderedBeverageID, GuestsOrder guestsOrder, Employee bartender, Beverages beverage,
			Integer quantity, String orderedBeverageNote, Boolean isAccepted, Boolean isDone, Timestamp acceptedTime,
			Timestamp doneTime) {
		super();
		this.orderedBeverageID = orderedBeverageID;
		this.guestsOrder = guestsOrder;
		this.bartender = bartender;
		this.beverage = beverage;
		this.quantity = quantity;
		this.orderedBeverageNote = orderedBeverageNote;
		this.IsAccepted = isAccepted;
		this.IsDone = isDone;
		this.acceptedTime = acceptedTime;
		this.doneTime = doneTime;
	}

	public Integer getOrderedBeverageID() {
		return orderedBeverageID;
	}

	public void setOrderedBeverageID(Integer orderedBeverageID) {
		this.orderedBeverageID = orderedBeverageID;
	}

	public GuestsOrder getGuestsOrder() {
		return guestsOrder;
	}

	public void setGuestsOrder(GuestsOrder guestsOrder) {
		this.guestsOrder = guestsOrder;
	}

	public Employee getBartender() {
		return bartender;
	}

	public void setBartender(Employee bartender) {
		this.bartender = bartender;
	}

	public Beverages getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverages beverage) {
		this.beverage = beverage;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOrderedBeverageNote() {
		return orderedBeverageNote;
	}

	public void setOrderedBeverageNote(String orderedBeverageNote) {
		this.orderedBeverageNote = orderedBeverageNote;
	}

	public Boolean getIsAccepted() {
		return IsAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		IsAccepted = isAccepted;
	}

	public Boolean getIsDone() {
		return IsDone;
	}

	public void setIsDone(Boolean isDone) {
		IsDone = isDone;
	}

	public Timestamp getAcceptedTime() {
		return acceptedTime;
	}

	public void setAcceptedTime(Timestamp acceptedTime) {
		this.acceptedTime = acceptedTime;
	}

	public Timestamp getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(Timestamp doneTime) {
		this.doneTime = doneTime;
	}
	
	

}

