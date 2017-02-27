package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Beverages;
import netgloo.models.GuestsOrder;
import netgloo.models.Restaurant;

public interface BeveragesDao extends CrudRepository<Beverages, Long> {
	
	
	public ArrayList<Beverages> findAllByRestaurantId(Restaurant rest);
	public Beverages findByBeveragesId(Long beveragesId);

}
