package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Beverages;
import netgloo.models.Menu;
import netgloo.models.Restaurant;

public interface MenuDao extends CrudRepository<Menu, Long> {
	
	public ArrayList<Menu> findAllByRestaurantId(Restaurant rest);

}
