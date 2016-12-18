package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Restaurant;

public interface RestaurantDao extends CrudRepository<Restaurant, String>{


}