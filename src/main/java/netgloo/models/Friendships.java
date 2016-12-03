package netgloo.models;

import java.io.Serializable;

import javax.persistence.Entity;
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
	@JsonBackReference("user-friendships")
	@ManyToOne
	@JoinColumn(name="love_giver", referencedColumnName="user_id", nullable=false)
	private User love_giver;
	
	@Id
	@JsonBackReference("user-friendships")
	@ManyToOne
	@JoinColumn(name="love_taker", referencedColumnName="user_id", nullable=false)
	private User love_taker;
	
	@NotNull
	private Boolean friendship_accepted;

	public Friendships(User love_giver, User love_taker, Boolean friendship_accepted) {
		super();
		this.love_giver = love_giver;
		this.love_taker = love_taker;
		this.friendship_accepted = friendship_accepted;
	}

	public User getLove_giver() {
		return love_giver;
	}

	public void setLove_giver(User love_giver) {
		this.love_giver = love_giver;
	}

	public User getLove_taker() {
		return love_taker;
	}

	public void setLove_taker(User love_taker) {
		this.love_taker = love_taker;
	}

	public Boolean getFriendship_accepted() {
		return friendship_accepted;
	}

	public void setFriendship_accepted(Boolean friendship_accepted) {
		this.friendship_accepted = friendship_accepted;
	}


	
	
}
