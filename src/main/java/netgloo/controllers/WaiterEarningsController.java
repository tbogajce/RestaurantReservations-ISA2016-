package netgloo.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.RestaurantDao;
import netgloo.dao.WaiterEarningsDao;
import netgloo.models.DatesForWSImitation;
import netgloo.models.Menu;
import netgloo.models.REOFL;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;
import netgloo.models.WEOFL;
import netgloo.models.WSOFL;
import netgloo.models.WaiterEarnings;
import netgloo.models.WorkingShift;

@RestController
@RequestMapping("/waiterEarningsController")
public class WaiterEarningsController {

	@Autowired
	private RestaurantDao restDao;

	@Autowired
	private WaiterEarningsDao weDao;

	ArrayList<WaiterEarnings> listOfWaiterEarnings = new ArrayList<WaiterEarnings>();

	@RequestMapping(value = "/getWaiterEarnings", method = RequestMethod.POST)
	public ArrayList<WEOFL> getWaiterEarnings(HttpServletRequest request) {

		ArrayList<WaiterEarnings> waiterEarningsList = new ArrayList<WaiterEarnings>();

		ArrayList<WEOFL> weoflList = new ArrayList<WEOFL>();

		weoflList.clear();

		try {
			
			RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			Restaurant restaurant = rmkkk.getRestaurantId();

			if (restaurant != null) {
				System.out.println("WE restaurant != null");
				waiterEarningsList = weDao.findAllByRestaurant(restaurant);

				for (WaiterEarnings we : waiterEarningsList) {
					System.out.println("RESTORAN ID: " + restaurant.getRestaurantId());
					System.out.println("WaiterEarnings we : waiterEarningsList");
					if (restaurant.getRestaurantId() == we.getRestaurant().getRestaurantId()) {

						System.out.println("USAO U WS IF!");

						WEOFL primerak = new WEOFL(we.getWaiter().getUserId().getUserName(),
								we.getWaiter().getUserId().getUserSurname(), we.getDateOfEarning().toString(),
								we.getEarned());

						System.out.println("XX: " + we.getWaiter().getUserId().getUserName() + " "
								+ we.getWaiter().getUserId().getUserSurname() + " "
								+ we.getDateOfEarning().toString() + " " + we.getEarned());
						
						weoflList.add(primerak);
					}
				}

			}

			return weoflList;

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("--------------EXCEPTION ... ---------------------");
			return weoflList;
		}
	}
	
	@RequestMapping(value = "/getRestaurantEarnings", method = RequestMethod.POST)
	public REOFL getRestaurantEarnings(HttpServletRequest request) {

		ArrayList<WaiterEarnings> waiterEarningsList = new ArrayList<WaiterEarnings>();

		ArrayList<WEOFL> weoflList = new ArrayList<WEOFL>();
		
		ArrayList<REOFL> reoflList = new ArrayList<REOFL>();
		REOFL primerak2 = null;
		weoflList.clear();
		float suma = 0;

		try {
			
			RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			Restaurant restaurant = rmkkk.getRestaurantId();

			if (restaurant != null) {
				System.out.println("WE restaurant != null");
				waiterEarningsList = weDao.findAllByRestaurant(restaurant);

				for (WaiterEarnings we : waiterEarningsList) {
					System.out.println("RESTORAN ID: " + restaurant.getRestaurantId());
					System.out.println("WaiterEarnings we : waiterEarningsList");
					if (restaurant.getRestaurantId().equals(we.getRestaurant().getRestaurantId())) {

						System.out.println("USAO U RE IF!");

						WEOFL primerak = new WEOFL(we.getWaiter().getUserId().getUserName(),
								we.getWaiter().getUserId().getUserSurname(), we.getDateOfEarning().toString(),
								we.getEarned());

						System.out.println("XX: " + we.getWaiter().getUserId().getUserName() + " "
								+ we.getWaiter().getUserId().getUserSurname() + " "
								+ we.getDateOfEarning().toString() + " " + we.getEarned());
						suma+=we.getEarned();
						System.out.println("OVO JE BROJ: " + we.getEarned());
						System.out.println("OVO JE SUMA " + suma);
						
						weoflList.add(primerak);
						
						
					}
					
					primerak2 = new REOFL(restaurant.getRestaurantName(), suma);
					primerak2.setRestaurantEarned(suma);
					reoflList.add(primerak2);
				}

			}

			return primerak2;

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("--------------EXCEPTION ... ---------------------");
			return primerak2;
		}
	}

}
