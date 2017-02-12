package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;

public interface OrderedMealDao extends CrudRepository<OrderedMeal, Integer>{

}
