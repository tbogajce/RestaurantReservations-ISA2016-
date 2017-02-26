package netgloo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.RestaurantDao;
import netgloo.dao.RestaurantManagerDao;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;
import netgloo.models.RestaurantManagerPom;
import netgloo.models.SystemManager;

@RestController
@RequestMapping("/restaurantManagerController")
public class RestaurantManagerController {

	@Autowired
	private RestaurantManagerDao restManDao;

	@Autowired
	private RestaurantDao restDao;

	ArrayList<RestaurantManager> listOfRestaurantManagers = new ArrayList<RestaurantManager>();

	@RequestMapping(value = "/createNewRestaurantManager", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createRestaurantManager(@RequestBody RestaurantManagerPom rm1) {
		System.out.println("ispis iz managera --" + rm1.getRestaurantId());
		// msm da ne radi jer ti sa restom posaljes ID, recimo 1
		// i pokusavas da u objekat RestaurantManager ubacis samo 1
		// moras ubaciti, kao 6-ti parametar, citav objekat..ne moze on skontati
		// na osnovu 1, da je to taj objekat
		// ispis u konzoli je da je restauran_id null i da ne moze biti null..to
		// je zato jer prosledjujes kao broj, a ne citav objekat
		// nazovi me :D
		try {

			Restaurant restaurant = restDao.findByRestaurantId(Long.parseLong(rm1.getRestaurantId()));

			RestaurantManager restaurantManager = null;
			restaurantManager = new RestaurantManager(rm1.getRestaurantManagerNickId(), rm1.getRestaurantManagerMail(),
					rm1.getRestaurantManagerName(), rm1.getRestaurantManagerSurname(),
					rm1.getRestaurantManagerPassword(), restaurant);
			restManDao.save(restaurantManager);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

	@RequestMapping(value = "/loginRestaurantManager", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String loginRestaurantManager(@RequestBody RestaurantManager rm1, HttpServletRequest request) {
		try {
			RestaurantManager restaurantManager = restManDao.findOne(rm1.getRestaurantManagerNickId());
			if (restaurantManager != null) {
				String nick = String.valueOf(restaurantManager.getRestaurantManagerNickId());
				String pass = String.valueOf(restaurantManager.getRestaurantManagerPassword());
				if (nick.equals(rm1.getRestaurantManagerNickId()) && pass.equals(rm1.getRestaurantManagerPassword())) {
					request.getSession().setAttribute("restaurantManager", restaurantManager);
					return "restaurantManager";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

	@RequestMapping(value = "/updateRestaurant", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	@ResponseBody
	public String updateRestaurant(@RequestBody Restaurant r1, RestaurantManager rm1) {
		try {
			
			if (restDao.findByRestaurantId(r1.getRestaurantId()) == rm1.getRestaurantId()) {
				System.out.println("ID menadzera i restorana JESU isti");
				if (restDao.findByRestaurantId(r1.getRestaurantId()) != null) {

					Restaurant restaurant = restDao.findByRestaurantId(r1.getRestaurantId());
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
			}else{
				System.out.println("ID menadzera i restorana NISU isti");
			}
		} catch (Exception ex) {
			return "Error updating restaurant: " + ex.toString();
		}
		return null;

	}
	
	@RequestMapping(value = "/restaurantData", method = RequestMethod.GET)
	public Restaurant restaurantData(HttpServletRequest request) {
		try{
			if(request.getSession().getAttribute("restaurant")!=null)
			{
				Restaurant restaurant =(Restaurant) request.getSession().getAttribute("restaurant");
				return restaurant;
			}
			
		} catch (Exception e) {
			System.out.println("no restaurant data");
		}
		return null;
	}

}
