package netgloo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.BeveragesDao;
import netgloo.dao.RestaurantDao;
import netgloo.models.Beverages;
import netgloo.models.BeveragesPom;
import netgloo.models.Friendships;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;
import netgloo.models.SystemManager;
import netgloo.models.User;

@RestController
@RequestMapping("/beveragesController")
public class BeveragesController {

	@Autowired
	private BeveragesDao bavDao;

	@Autowired
	private RestaurantDao restDao;

	ArrayList<Beverages> listOfBeverages = new ArrayList<Beverages>();

	@RequestMapping(value = "/createNewBeverage", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createSystemManager(@RequestBody BeveragesPom b1, HttpServletRequest request) {

		try {
			
			RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			Restaurant restaurant = rmkkk.getRestaurantId();
			
			//Restaurant restaurant = restDao.findByRestaurantId(Long.parseLong(b1.getRestaurantId()));

			Beverages beverages = null;
			// String user_reg_date = new
			// SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			beverages = new Beverages(restaurant, b1.getBeveragesId(), b1.getBeveragesDescription(),
					b1.getBeveragesName(), b1.getBeveragesPrice(),0,0);
			bavDao.save(beverages);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}
	
	@RequestMapping(value = "/getBeverages", method = RequestMethod.GET)
	public ArrayList<Beverages> getBeverages(@RequestBody Beverages b, HttpServletRequest request) {
		System.out.println("JE LI RAVNO OVO?");
		listOfBeverages.clear();
		try {
			System.out.println("BARBE IMAL RIBE?");
			
			RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			Restaurant restaurant = rmkkk.getRestaurantId();
			
			Beverages beverages = b;
			
			ArrayList<Beverages> fs = (ArrayList<Beverages>) bavDao.findAllByRestaurantId(restaurant); 
			for (int i = 0; i < fs.size(); i++) {
					//listOfBeverages.add(fs.get(i).beverages);
					listOfBeverages.add(i, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfBeverages;
	}

}