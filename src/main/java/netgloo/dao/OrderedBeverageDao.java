package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Employee;
import netgloo.models.OrderedBeverage;

public interface OrderedBeverageDao extends CrudRepository<OrderedBeverage, Integer>{
	
	
	//public OrderedBeverage findByorderedBeverageID()

}
