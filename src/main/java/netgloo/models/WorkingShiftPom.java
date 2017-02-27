package netgloo.models;

import java.sql.Timestamp;

public class WorkingShiftPom {
	
	private String worker;
	private Timestamp shiftBeginningTime;
	private Timestamp shiftEndTime;
	private String note;
	
	public WorkingShiftPom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkingShiftPom(String worker, Timestamp shiftBeginningTime, Timestamp shiftEndTime, String note) {
		super();
		this.worker = worker;
		this.shiftBeginningTime = shiftBeginningTime;
		this.shiftEndTime = shiftEndTime;
		this.note = note;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public Timestamp getShiftBeginningTime() {
		return shiftBeginningTime;
	}

	public void setShiftBeginningTime(Timestamp shiftBeginningTime) {
		this.shiftBeginningTime = shiftBeginningTime;
	}

	public Timestamp getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(Timestamp shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
