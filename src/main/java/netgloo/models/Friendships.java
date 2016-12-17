package netgloo.models;

import java.io.Serializable;

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
@Table(name = "friendships")
public class Friendships implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long friendshipId;
	
	@JsonBackReference("user-friendships")
	@ManyToOne
	@JoinColumn(name="loveGiver", referencedColumnName="userId", nullable=false)
	private User loveGiver;
	
	
	@JsonBackReference("user-friendships")
	@ManyToOne
	@JoinColumn(name="loveTaker", referencedColumnName="userId", nullable=false)
	private User loveTaker;
	
	@NotNull
	private Boolean friendshipAccepted;
	
	
	public Friendships() {
		super();
	}
	
	



	public Long getFriendshipId() {
		return friendshipId;
	}





	public void setFriendshipId(Long friendshipId) {
		this.friendshipId = friendshipId;
	}





	public User getLoveTaker() {
		return loveTaker;
	}





	public Boolean getFriendshipAccepted() {
		return friendshipAccepted;
	}





	public void setFriendshipAccepted(Boolean friendshipAccepted) {
		this.friendshipAccepted = friendshipAccepted;
	}





	public void setLoveTaker(User loveTaker) {
		this.loveTaker = loveTaker;
	}





	public Friendships(User loveGiver, User love_taker, Boolean friendship_accepted) {
		super();
		this.loveGiver = loveGiver;
		this.loveTaker = love_taker;
		this.friendshipAccepted = friendship_accepted;
	}



	public Long getFriendship_id() {
		return friendshipId;
	}



	public void setFriendship_id(Long friendship_id) {
		this.friendshipId = friendship_id;
	}



	public User getLoveGiver() {
		return loveGiver;
	}



	public void setLoveGiver(User loveGiver) {
		this.loveGiver = loveGiver;
	}



	public User getLove_taker() {
		return loveTaker;
	}

	public void setLove_taker(User love_taker) {
		this.loveTaker = love_taker;
	}

	public Boolean getFriendship_accepted() {
		return friendshipAccepted;
	}

	public void setFriendship_accepted(Boolean friendship_accepted) {
		this.friendshipAccepted = friendship_accepted;
	}


	
	
}
