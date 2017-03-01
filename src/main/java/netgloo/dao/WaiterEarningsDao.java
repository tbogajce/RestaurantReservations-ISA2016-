package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Bill;
import netgloo.models.Restaurant;
import netgloo.models.WaiterEarnings;

public interface WaiterEarningsDao extends CrudRepository<WaiterEarnings, Long>{
	
	public ArrayList<WaiterEarnings> findAllByRestaurant(Restaurant restaurant);
	
}
