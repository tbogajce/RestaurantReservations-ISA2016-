package netgloo.models;

import java.util.Date;

public class DatesForWSImitation {

	private String startDate;
	
	private String endDate;

	public DatesForWSImitation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatesForWSImitation(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
}
