package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.GuestsOrder;
import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;

public interface OrderedMealDao extends CrudRepository<OrderedMeal, Integer>{
	
	
	public ArrayList<OrderedMeal> findAllByGuestsOrder(GuestsOrder go);

}
