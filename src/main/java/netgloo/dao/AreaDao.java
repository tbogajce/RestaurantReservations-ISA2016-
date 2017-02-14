package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Area;
import netgloo.models.DiningTable;
import netgloo.models.Restaurant;

public interface AreaDao extends CrudRepository<Area, Long>{
	
	
	
	public ArrayList<Area> findAllByRestaurant(Restaurant restaurant);

}
