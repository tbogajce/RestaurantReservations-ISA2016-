package netgloo.models;

public class WSOFL {

	private String name;
	private String lastName;
	private String start;
	private String finish;
	
	
	public WSOFL(String name, String lastName, String start, String finish) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.start = start;
		this.finish = finish;
	}
	public WSOFL() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getFinish() {
		return finish;
	}
	public void setFinish(String finish) {
		this.finish = finish;
	}
	
	
	
	
}
