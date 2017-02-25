package netgloo.models;

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
@Table(name = "inviteFriend")
public class InviteFriend {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer inviteFriendId;
	
	@JsonBackReference("tableReservation-inviteFriend")
	@ManyToOne
	@JoinColumn(name="tableReservationId", referencedColumnName="tableReservationId", nullable=false)
	private TableReservation tableReservationId;
	
	@JsonBackReference("user-inviteFriend")
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName="userId", nullable=false)
	private User userId;
	
	@NotNull
	private Boolean accepted;

	public InviteFriend() {
		super();
	}



	public InviteFriend(TableReservation tableReservationId, User userId, Boolean accepted) {
		super();
		this.tableReservationId = tableReservationId;
		this.userId = userId;
		this.accepted = accepted;
	}



	public Integer getInviteFriendId() {
		return inviteFriendId;
	}

	public void setInviteFriendId(Integer inviteFriendId) {
		this.inviteFriendId = inviteFriendId;
	}



	public TableReservation getTableReservationId() {
		return tableReservationId;
	}

	public void setTableReservationId(TableReservation tableReservationId) {
		this.tableReservationId = tableReservationId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	
	
	
}
