package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Restaurant;
import netgloo.models.Segment;

public interface SegmentDao extends CrudRepository<Segment, Long>{
	
	public ArrayList<Segment> findAllByRestaurant(Restaurant restaurant);

}
