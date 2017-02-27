package netgloo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.RestaurantDao;
import netgloo.models.Restaurant;
import netgloo.models.SystemManager;

@RestController
@RequestMapping("/restaurantController")
public class RestaurantController {

	@Autowired
	private RestaurantDao restDao;

	ArrayList<Restaurant> listOfRestaurants = new ArrayList<Restaurant>();

	@RequestMapping(value = "/createNewRestaurant", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createRestaurant(@RequestBody Restaurant r1) {

		System.out.println("Usao u Restaurant Controller/Create New Restaurant");

		try {
			Restaurant restaurant = null;
			restaurant = new Restaurant(r1.getRestaurantName(), r1.getRestaurantType(), r1.getRestaurantCoordinates(),
					r1.getRestaurantAdress(), r1.getRestaurantRate(), r1.getRestaurantVisitsNumber(),
					r1.getRestaurantIncome(),0,0);
			restDao.save(restaurant);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}
	
	/*
	@RequestMapping(value = "/updateRestaurant", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	@ResponseBody
	public String updateRestaurant(@RequestBody Restaurant r1) {
		try {
			if (restDao.findOne(r1.getRestaurantName()) != null) {

				Restaurant restaurant = restDao.findOne(r1.getRestaurantName());
				restaurant.setRestaurantName(r1.getRestaurantName());
				restaurant.setRestaurantType(r1.getRestaurantType());
				restaurant.setRestaurantCoordinates(r1.getRestaurantCoordinates());
				restaurant.setRestaurantAdress(r1.getRestaurantAdress());
				restaurant.setRestaurantRate(r1.getRestaurantRate());
				restaurant.setRestaurantVisitsNumber(r1.getRestaurantVisitsNumber());
				restaurant.setRestaurantIncome(r1.getRestaurantIncome());
				restDao.save(restaurant);
				return "OK";
			} else {
				return "NOT_OK";
			}
		} catch (Exception ex) {
			return "Error updating systemManager: " + ex.toString();
		}

	} */
}
