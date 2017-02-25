package netgloo.models;

public class OrderImmitation {
	
	private Integer orderId;
	
	
	// m - meal, b - beverage
	private String whatIsIt;

	private String whatWasOrdered;
	
	private Long whatWasOrderedId;
	
	private Integer quantity;
	
	private String note;
	
	private String accepted;
	
	private String done;
	
	private String canceled;
	
	private String employee;
	
	private Long table;
	
	private int guestOrderID;
	
	private String status;
	
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//yes - jeste novo, no - nije novo
	private String novitet;

	public OrderImmitation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderImmitation(Integer orderId,String whatWasOrdered, Integer quantity, String note, String accepted, String done,
			String employee, Long table,String whatIsIt,String canceled) {
		super();
		this.orderId=orderId;
		this.whatWasOrdered = whatWasOrdered;
		this.quantity = quantity;
		this.note = note;
		this.accepted = accepted;
		this.done = done;
		this.employee = employee;
		this.table = table;
		this.whatIsIt=whatIsIt;
		this.canceled=canceled;
	}
	
	public OrderImmitation(Integer orderId,String whatIsIt)
	{
		super();
		this.orderId=orderId;
		this.whatIsIt=whatIsIt;
	}
	
	
	
	
	

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public Long getWhatWasOrderedId() {
		return whatWasOrderedId;
	}

	public void setWhatWasOrderedId(Long whatWasOrderedId) {
		this.whatWasOrderedId = whatWasOrderedId;
	}

	public int getGuestOrderID() {
		return guestOrderID;
	}

	public void setGuestOrderID(int guestOrderID) {
		this.guestOrderID = guestOrderID;
	}

	public String getNovitet() {
		return novitet;
	}

	public void setNovitet(String novitet) {
		this.novitet = novitet;
	}

	public String getWhatIsIt() {
		return whatIsIt;
	}

	public void setWhatIsIt(String whatIsIt) {
		this.whatIsIt = whatIsIt;
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
