package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Employee;
import netgloo.models.GuestsOrder;
import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;

public interface OrderedBeverageDao extends CrudRepository<OrderedBeverage, Integer>{
	
	
	//public OrderedBeverage findByorderedBeverageID()
	public ArrayList<OrderedBeverage> findAllByGuestsOrder(GuestsOrder go);

}
