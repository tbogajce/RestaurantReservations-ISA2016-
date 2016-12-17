package netgloo.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import netgloo.models.Friendships;
import netgloo.models.User;

public interface FriendshipsDao extends CrudRepository<Friendships, Integer>  {
	
	public List<Friendships> findByLoveGiver(User love_giver);
}
