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
	private Long friendship_id;
	
	@JsonBackReference("user-friendships")
	@ManyToOne
	@JoinColumn(name="love_giver", referencedColumnName="user_id", nullable=false)
	private User loveGiver;
	
	
	@JsonBackReference("user-friendships")
	@ManyToOne
	@JoinColumn(name="love_taker", referencedColumnName="user_id", nullable=false)
	private User love_taker;
	
	@NotNull
	private Boolean friendship_accepted;
	
	
	public Friendships() {
		super();
	}



	public Friendships(User loveGiver, User love_taker, Boolean friendship_accepted) {
		super();
		this.loveGiver = loveGiver;
		this.love_taker = love_taker;
		this.friendship_accepted = friendship_accepted;
	}



	public Long getFriendship_id() {
		return friendship_id;
	}



	public void setFriendship_id(Long friendship_id) {
		this.friendship_id = friendship_id;
	}



	public User getLoveGiver() {
		return loveGiver;
	}



	public void setLoveGiver(User loveGiver) {
		this.loveGiver = loveGiver;
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
