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
@Table(name="orderedMeal")
public class OrderedMeal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderedMealID;
	
	@JsonBackReference("guestsOrder-orderedMeal")
	@ManyToOne
	@JoinColumn(name="guestsOrder", referencedColumnName="orderID", nullable=false)
	private GuestsOrder guestsOrder;
	
	
	@JsonBackReference("employee-orderedMeal")
	@ManyToOne
	@JoinColumn(name="cook", referencedColumnName="employeeId", nullable=true)
	private Employee cook;
	
	@JsonBackReference("menu-orderedMeal")
	@ManyToOne
	@JoinColumn(name="menu", referencedColumnName="menuMealId", nullable=false)
	private Menu menu;
	
	@NotNull
	private Integer quantity;
	
	@Column(nullable=true)
	private String orderedMealNote;
	
	//@Column(nullable=true)
	private Boolean IsAccepted;
	
	@Column(nullable=true)
	private Boolean isCanceled;
	
	@Column(nullable=true)
	private Boolean IsDone;
	
	@Column(nullable=true)
	private Timestamp acceptedTime;
	
	@Column(nullable=true)
	private Timestamp doneTime;

	public OrderedMeal(Integer orderedMealID, GuestsOrder guestsOrder, Employee cook, Menu menu, Integer quantity,
			String orderedMealNote, Boolean isAccepted, Boolean isDone, Timestamp acceptedTime, Timestamp doneTime) {
		super();
		this.orderedMealID = orderedMealID;
		this.guestsOrder = guestsOrder;
		this.cook = cook;
		this.menu = menu;
		this.quantity = quantity;
		this.orderedMealNote = orderedMealNote;
		this.IsAccepted = isAccepted;
		this.IsDone = isDone;
		this.acceptedTime = acceptedTime;
		this.doneTime = doneTime;
	}
	
	public OrderedMeal(GuestsOrder guestsOrder, Menu menu, Integer quantity,
			String orderedMealNote) {
		super();
		this.guestsOrder = guestsOrder;
		this.menu = menu;
		this.quantity = quantity;
		this.orderedMealNote = orderedMealNote;
	}
	
	
	
	

	public OrderedMeal() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Boolean getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(Boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public Integer getOrderedMealID() {
		return orderedMealID;
	}

	public void setOrderedMealID(Integer orderedMealID) {
		this.orderedMealID = orderedMealID;
	}

	public GuestsOrder getGuestsOrder() {
		return guestsOrder;
	}

	public void setGuestsOrder(GuestsOrder guestsOrder) {
		this.guestsOrder = guestsOrder;
	}

	public Employee getCook() {
		return cook;
	}

	public void setCook(Employee cook) {
		this.cook = cook;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOrderedMealNote() {
		return orderedMealNote;
	}

	public void setOrderedMealNote(String orderedMealNote) {
		this.orderedMealNote = orderedMealNote;
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