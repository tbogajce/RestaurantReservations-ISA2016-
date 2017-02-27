package netgloo.models;

public class InviteFriendPom {
	public String tableReservationId;
	public String menuId;
	public String beverageId;
	public String email;
	public InviteFriendPom() {
		super();
	}
	public InviteFriendPom(String tableReservationId, String email) {
		super();
		this.tableReservationId = tableReservationId;
		this.email = email;
	}
	
	
	public InviteFriendPom(String tableReservationId, String menuId, String beverageId) {
		super();
		this.tableReservationId = tableReservationId;
		this.menuId = menuId;
		this.beverageId = beverageId;
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
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getBeverageId() {
		return beverageId;
	}
	public void setBeverageId(String beverageId) {
		this.beverageId = beverageId;
	}
	
	
	
	
}
