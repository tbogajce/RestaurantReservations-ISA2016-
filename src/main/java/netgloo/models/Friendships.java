package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "friendships")
public class Friendships {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long friendship_id;
	
	@ManyToMany
	@JoinColumn(name = "user_id", table = "user")
	private Long love_giver;
	
	@ManyToMany
	@JoinColumn(name = "user_id", table = "user")
	private Long love_taker;
	
	@NotNull
	private Boolean friendship_accepted;

	public Friendships(Long friendship_id, Long love_giver, Long love_taker, Boolean friendship_accepted) {
		super();
		this.friendship_id = friendship_id;
		this.love_giver = love_giver;
		this.love_taker = love_taker;
		this.friendship_accepted = friendship_accepted;
	}

	public Long getFriendship_id() {
		return friendship_id;
	}

	public void setFriendship_id(Long friendship_id) {
		this.friendship_id = friendship_id;
	}

	public Long getLove_giver() {
		return love_giver;
	}

	public void setLove_giver(Long love_giver) {
		this.love_giver = love_giver;
	}

	public Long getLove_taker() {
		return love_taker;
	}

	public void setLove_taker(Long love_taker) {
		this.love_taker = love_taker;
	}

	public Boolean getFriendship_accepted() {
		return friendship_accepted;
	}

	public void setFriendship_accepted(Boolean friendship_accepted) {
		this.friendship_accepted = friendship_accepted;
	}
	
	
}
