package netgloo.models;

public class InviteFriendPom {
	public String tableReservationId;
	public String email;
	public InviteFriendPom() {
		super();
	}
	public InviteFriendPom(String tableReservationId, String email) {
		super();
		this.tableReservationId = tableReservationId;
		this.email = email;
	}
	public String getTableReservationId() {
		return tableReservationId;
	}
	public void setTableReservationId(String tableReservationId) {
		this.tableReservationId = tableReservationId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
