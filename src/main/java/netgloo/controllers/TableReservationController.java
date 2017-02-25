package netgloo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.DinningTableDao;
import netgloo.dao.FriendshipsDao;
import netgloo.dao.InviteFriendDao;
import netgloo.dao.RestaurantDao;
import netgloo.dao.TableReservationDao;
import netgloo.models.DiningTable;
import netgloo.models.Friendships;
import netgloo.models.Restaurant;
import netgloo.models.TableReservation;
import netgloo.models.TableReservationPom;
import netgloo.models.User;

@RestController
@RequestMapping("/tableReservation")
public class TableReservationController {
	
	@Autowired
	private TableReservationDao tableReservationDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private InviteFriendDao inviteFriendDao;
	
	@Autowired
	private DinningTableDao dinningTableDao;
	
	@Autowired
	private FriendshipsDao friendshipsDao;
	
	public ArrayList<Restaurant> restaurantCombo = new ArrayList<Restaurant>();
	public ArrayList<DiningTable> diningTableList = new ArrayList<DiningTable>();
	public ArrayList<TableReservation> reservedRestaurant = new ArrayList<TableReservation>();
	public ArrayList<User> allFriends = new ArrayList<User>();
	
	//METODE--------------------------
	
	@RequestMapping(value = "/getReservedRestaurant", method = RequestMethod.GET)
	public ArrayList<TableReservation> getReservedRestaurant(HttpServletRequest request) {
		reservedRestaurant.clear();
		List<TableReservation> lu = (List<TableReservation>) tableReservationDao.findAll();
		reservedRestaurant = (ArrayList<TableReservation>) lu;
		return reservedRestaurant;
	}
	
	@RequestMapping(value = "/getRestaurantCombo", method = RequestMethod.GET)
	public ArrayList<Restaurant> getRestaurantCombo(HttpServletRequest request) {
		restaurantCombo.clear();
		List<Restaurant> lu = (List<Restaurant>) restaurantDao.findAll();
		restaurantCombo = (ArrayList<Restaurant>) lu;
		return restaurantCombo;
	}
	
	@RequestMapping(value = "/getAllFriends", method = RequestMethod.GET)
	public ArrayList<User> getAllFriends(HttpServletRequest request) {
		allFriends.clear();
//		List<Restaurant> lu = (List<Restaurant>) restaurantDao.findAll();
//		restaurantCombo = (ArrayList<Restaurant>) lu;
		User user = (User) request.getSession().getAttribute("user");
		List<Friendships> lu = (List<Friendships>) friendshipsDao.findByLoveGiver(user);
		for(int i=0; i<lu.size();i++) {
			if(lu.get(i).getFriendship_accepted())
				allFriends.add(lu.get(i).getLoveTaker());
		}
		return allFriends;
	}
	
	@RequestMapping(value = "/restaurantTables", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ArrayList<DiningTable> restaurantTables(@RequestBody Restaurant restaurant, HttpServletRequest request) {
		diningTableList.clear();
		List<DiningTable> dt = (List<DiningTable>) dinningTableDao.findAll();
		for(int i=0; i<dt.size();i++) {
			if(dt.get(i).getRestaurant().getRestaurantId().equals(restaurant.getRestaurantId())){
				diningTableList.add(dt.get(i));
			}
		}
		System.out.println("SIZE DINING TABLE JE:  " + diningTableList.size());
		return diningTableList;
	}
	
	@RequestMapping(value = "/getReservedRestaurantData", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public TableReservation getReservedRestaurantData(@RequestBody TableReservationPom tableReservationPom, HttpServletRequest request) {
		String tableResId = tableReservationPom.getTableReservationId();
		TableReservation tableReservation = tableReservationDao.findByTableReservationId(Integer.parseInt(tableResId));
		
		return tableReservation;
	}
	
	@RequestMapping(value = "/restaurantReservation", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String restaurantReservation(@RequestBody TableReservationPom tableReservationPom, HttpServletRequest request) {
		System.out.println(tableReservationPom.getRestaurantId());
		System.out.println(tableReservationPom.getDate());
		System.out.println(tableReservationPom.getTime());
		System.out.println(tableReservationPom.getHours());
		System.out.println(tableReservationPom.getDiningTableId());
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			Restaurant restaurant = restaurantDao.findByRestaurantId(Long.parseLong(tableReservationPom.getRestaurantId()));
			DiningTable diningTable = dinningTableDao.findByGeneralTableID(Long.parseLong(tableReservationPom.getDiningTableId()));

			TableReservation tableReservation = new TableReservation(restaurant, tableReservationPom.getDate(),
					tableReservationPom.getTime(), Integer.parseInt(tableReservationPom.getHours()), diningTable, user);
			tableReservationDao.save(tableReservation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "OK";
	}
	
}
