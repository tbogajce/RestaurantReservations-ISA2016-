package netgloo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.RestaurantDao;
import netgloo.models.Restaurant;

@RestController
@RequestMapping("/restaurantController")
public class RestaurantController {

	@Autowired
	private RestaurantDao restDao;
	
	ArrayList<Restaurant> listOfRestaurants = new ArrayList<Restaurant>();
	
	@RequestMapping(value = "/createNewRestaurant", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String createRestaurant(@RequestBody Restaurant r1) {
		
		System.out.println("Usao u Restaurant Controller/Create New Restaurant");

		try {
			Restaurant restaurant = null;
			restaurant = new Restaurant(r1.getRestaurantName(), r1.getRestaurantType(), 
					r1.getRestaurantCoordinates(), r1.getRestaurantAdress(), r1.getRestaurantRate(), 
					r1.getRestaurantVisitsNumber(), r1.getRestaurantIncome());
			restDao.save(restaurant);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}
	
}
