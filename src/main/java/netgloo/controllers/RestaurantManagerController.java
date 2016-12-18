package netgloo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.RestaurantManagerDao;
import netgloo.models.RestaurantManager;

@RestController
@RequestMapping("/restaurantManagerController")
public class RestaurantManagerController {
	
	@Autowired
	private RestaurantManagerDao restManDao;
	
	ArrayList<RestaurantManager> listOfRestaurantManagers = new ArrayList<RestaurantManager>();
	
	@RequestMapping(value = "/createNewRestaurantManager", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String createRestaurantManager(@RequestBody RestaurantManager rm1) {

		try {
			RestaurantManager restaurantManager = null;
			restaurantManager = new RestaurantManager(rm1.getRestaurantManagerNickId(),rm1.getRestaurantManagerMail(),
					rm1.getRestaurantManagerName(),rm1.getRestaurantManagerSurname(),rm1.getRestaurantManagerPassword());
			restManDao.save(restaurantManager);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}
	
}
