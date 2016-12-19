package netgloo.models;

public class OrderImmitation {
	
	private Integer orderId;

	private String whatWasOrdered;
	
	private Integer quantity;
	
	private String note;
	
	private String accepted;
	
	private String done;
	
	private String employee;
	
	private Long table;

	public OrderImmitation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderImmitation(Integer orderId,String whatWasOrdered, Integer quantity, String note, String accepted, String done,
			String employee, Long table) {
		super();
		this.orderId=orderId;
		this.whatWasOrdered = whatWasOrdered;
		this.quantity = quantity;
		this.note = note;
		this.accepted = accepted;
		this.done = done;
		this.employee = employee;
		this.table = table;
	}
	
	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getWhatWasOrdered() {
		return whatWasOrdered;
	}

	public void setWhatWasOrdered(String whatWasOrdered) {
		this.whatWasOrdered = whatWasOrdered;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public Long getTable() {
		return table;
	}

	public void setTable(Long table) {
		this.table = table;
	}
	
	
	
	
	
	
	
	
	
}
