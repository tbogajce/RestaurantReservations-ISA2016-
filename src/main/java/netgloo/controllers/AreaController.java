package netgloo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.AreaDao;
import netgloo.dao.RestaurantDao;
import netgloo.models.Area;
import netgloo.models.Beverages;
import netgloo.models.BeveragesPom;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;

@RestController
@RequestMapping("/areaController")
public class AreaController {

	private static final Area ArrayList = null;

	@Autowired
	private RestaurantDao restDao;

	@Autowired
	private AreaDao areaDao;

	ArrayList<Area> listOfAreas = new ArrayList<Area>();

	@RequestMapping(value = "/createNewArea", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createArea(@RequestBody Area a1, HttpServletRequest request) {

		try {

			RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			Restaurant restaurant = rmkkk.getRestaurantId();

			System.out.println("USAO U KREIRANJE AREA");
			
			Area svi = (Area) 
			
			
			
			Area area = null;
			// String user_reg_date = new
			// SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			area = new Area(areaid, restaurant, a1.getAreaName(), a1.getSpaceX(), a1.getSpaceY(), a1.getNote());
			System.out.println("DODAO OBJEKAT AREA");
			areaDao.save(area);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

}
