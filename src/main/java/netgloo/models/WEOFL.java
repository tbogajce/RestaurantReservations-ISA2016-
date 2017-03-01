package netgloo.models;

public class WEOFL {
	
	private String name;
	private String lastName;
	private String date;
	private Float earned;
	
	public WEOFL() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WEOFL(String name, String lastName, String date, Float earned) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.date = date;
		this.earned = earned;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Float getEarned() {
		return earned;
	}

	public void setEarned(Float earned) {
		this.earned = earned;
	}
	
	
	
	
}
