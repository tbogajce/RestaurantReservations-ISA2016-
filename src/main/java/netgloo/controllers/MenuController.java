package netgloo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.MenuDao;
import netgloo.dao.RestaurantDao;
import netgloo.models.Beverages;
import netgloo.models.Menu;
import netgloo.models.MenuPom;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;

@RestController
@RequestMapping("/menuController")
public class MenuController {

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RestaurantDao restDao;

	ArrayList<Menu> listOfMenu = new ArrayList<Menu>();

	@RequestMapping(value = "/createNewMenu", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createMenu(@RequestBody MenuPom m1, HttpServletRequest request) {

		try {

			RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			Restaurant restaurant = rmkkk.getRestaurantId();

			// Restaurant restaurant =
			// restDao.findByRestaurantId(Long.parseLong(m1.getRestaurantId()));

			Menu menu = null;
			// String user_reg_date = new
			// SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			menu = new Menu(restaurant, m1.getMenuMealId(), m1.getMenuMealDescription(), m1.getMenuMealPrice(),
					m1.getMenuMealRate(), 0, 0);
			menuDao.save(menu);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public ArrayList<Menu> getMenu(HttpServletRequest request) {
		RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
		Restaurant restaurant = rmkkk.getRestaurantId();
		ArrayList<Menu> menu = (ArrayList<Menu>) menuDao.findAllByRestaurantId(restaurant);

		return menu;
	}

}
