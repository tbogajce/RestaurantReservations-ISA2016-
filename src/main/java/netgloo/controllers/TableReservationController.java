package netgloo.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.AreaDao;
import netgloo.dao.BeveragesDao;
import netgloo.dao.DinningTableDao;
import netgloo.dao.FriendshipsDao;
import netgloo.dao.GuestsOrderDao;
import netgloo.dao.InviteFriendDao;
import netgloo.dao.MenuDao;
import netgloo.dao.OrderedBeverageDao;
import netgloo.dao.OrderedMealDao;
import netgloo.dao.RestaurantDao;
import netgloo.dao.TableReservationDao;
import netgloo.dao.UserDao;
import netgloo.models.Area;
import netgloo.models.AreaPom;
import netgloo.models.Beverages;
import netgloo.models.DiningTable;
import netgloo.models.Friendships;
import netgloo.models.GuestsOrder;
import netgloo.models.InviteFriend;
import netgloo.models.InviteFriendPom;
import netgloo.models.Menu;
import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;
import netgloo.models.TablePrint;
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
	private UserDao userDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private BeveragesDao beveragesDao;

	@Autowired
	private SmtpMailSender smtpMailSender;

	@Autowired
	private FriendshipsDao friendshipsDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private GuestsOrderDao guestsOrderDao;

	@Autowired
	private OrderedBeverageDao orderedBeverageDao;

	@Autowired
	private OrderedMealDao orderedMealDao;
	

	public ArrayList<Restaurant> restaurantCombo = new ArrayList<Restaurant>();
	public ArrayList<DiningTable> diningTableList = new ArrayList<DiningTable>();
	public ArrayList<TableReservation> reservedRestaurant = new ArrayList<TableReservation>();
	public ArrayList<User> allFriends = new ArrayList<User>();
	public InviteFriend tr1 = null;
	public ArrayList<TableReservation> getTableReservation = new ArrayList<TableReservation>();
	public ArrayList<Area> listArea = new ArrayList<Area>();
	ArrayList<TablePrint> tp = new ArrayList<TablePrint>();
	
	ArrayList<DiningTable> dtList = new ArrayList<DiningTable>();

	public ArrayList<Menu> getRestaurantMenu = new ArrayList<Menu>();
	public ArrayList<Beverages> getRestaurantBeverage = new ArrayList<Beverages>();

	// METODE--------------------------

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

	@RequestMapping(value = "/getTablesReservation33", method = RequestMethod.GET)
	public ArrayList<TableReservation> getTablesReservation33(HttpServletRequest request) {
		getTableReservation.clear();
		
		User user = (User) request.getSession().getAttribute("user");
		List<InviteFriend> lu1 = (List<InviteFriend>) inviteFriendDao.findAll();
		for (int i = 0; i < lu1.size(); i++) {
			if (lu1.get(i).getUserId().getEmail().equals(user.getEmail()))
				getTableReservation.add(lu1.get(i).getTableReservationId());
		}
		List<TableReservation> lu = (List<TableReservation>) tableReservationDao.findAll();
		for(int i=0; i<lu.size(); i++) {
			if(lu.get(i).getUserId().getUser_id().equals(user.getUser_id()))
				getTableReservation.add(lu.get(i));
		}
		
		Set<TableReservation> set = new HashSet<TableReservation>(getTableReservation);
		getTableReservation.clear();
		getTableReservation.addAll(set);
		
		

		return getTableReservation;
	}

	@RequestMapping(value = "/getAllFriends", method = RequestMethod.GET)
	public ArrayList<User> getAllFriends(HttpServletRequest request) {
		allFriends.clear();
		// List<Restaurant> lu = (List<Restaurant>) restaurantDao.findAll();
		// restaurantCombo = (ArrayList<Restaurant>) lu;
		User user = (User) request.getSession().getAttribute("user");
		List<Friendships> lu = (List<Friendships>) friendshipsDao.findByLoveGiver(user);
		for (int i = 0; i < lu.size(); i++) {
			if (lu.get(i).getFriendship_accepted())
				allFriends.add(lu.get(i).getLoveTaker());
		}
		return allFriends;
	}

	@RequestMapping(value = "/restaurantTables", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public ArrayList<DiningTable> restaurantTables(@RequestBody Restaurant restaurant, HttpServletRequest request) {
		diningTableList.clear();
		List<DiningTable> dt = (List<DiningTable>) dinningTableDao.findAll();
		for (int i = 0; i < dt.size(); i++) {
			if (dt.get(i).getRestaurant().getRestaurantId().equals(restaurant.getRestaurantId())) {
				diningTableList.add(dt.get(i));
			}
		}
		System.out.println("SIZE DINING TABLE JE:  " + diningTableList.size());
		return diningTableList;
	}

	@RequestMapping(value = "/getAllAreas", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ArrayList<Area> getAllAreas(@RequestBody Restaurant restaurant, HttpServletRequest request) {
		listArea.clear();
		List<Area> dt = (List<Area>) areaDao.findAll();
		for (int i = 0; i < dt.size(); i++) {
			if (dt.get(i).getRestaurant().getRestaurantId().equals(restaurant.getRestaurantId())) {
				listArea.add(dt.get(i));
			}
		}
		System.out.println("SIZE AREA JE:  " + listArea.size());
		return listArea;
	}

	@RequestMapping(value = "/getTablePrint", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ArrayList<TablePrint> getTablePrint(@RequestBody AreaPom areaPom, HttpServletRequest request) {
		tp.clear();
		dtList.clear();
		String areaIdentificator = areaPom.getAreaID();
		Area areax = areaDao.findOne(Long.parseLong(areaIdentificator));
		dtList = dinningTableDao.findAllByArea(areax);
		
		for(DiningTable dt:dtList) {
			if(dt.isActive()==true) {
				
				tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),(long) -1, -1));
				System.out.println("PRINTANJE PRINT TABLE");
			}
		}

		return tp;
	}

	@RequestMapping(value = "/getReservedRestaurantData", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public TableReservation getReservedRestaurantData(@RequestBody TableReservationPom tableReservationPom,
			HttpServletRequest request) {
		try {
			String tableResId = tableReservationPom.getTableReservationId();
			TableReservation tableReservation = tableReservationDao
					.findByTableReservationId(Integer.parseInt(tableResId));

			return tableReservation;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getRestaurantMeal", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public ArrayList<Menu> getRestaurantMeal(@RequestBody TableReservationPom tableReservationPom,
			HttpServletRequest request) {
		try {
			getRestaurantMenu.clear();
			String tableResId = tableReservationPom.getTableReservationId();
			TableReservation tableReservation = tableReservationDao
					.findByTableReservationId(Integer.parseInt(tableResId));
			Restaurant restaurant = tableReservation.getRestaurantId();

			List<Menu> lu = (List<Menu>) menuDao.findAll();

			for (int i = 0; i < lu.size(); i++) {
				if (lu.get(i).getRestaurantId().getRestaurantId().equals(restaurant.getRestaurantId()))
					getRestaurantMenu.add(lu.get(i));
			}

			return getRestaurantMenu;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ---------BEVERAGES
	@RequestMapping(value = "/getRestaurantBeverages", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public ArrayList<Beverages> getRestaurantBeverages(@RequestBody TableReservationPom tableReservationPom,
			HttpServletRequest request) {
		try {
			getRestaurantBeverage.clear();
			String tableResId = tableReservationPom.getTableReservationId();
			TableReservation tableReservation = tableReservationDao
					.findByTableReservationId(Integer.parseInt(tableResId));
			Restaurant restaurant = tableReservation.getRestaurantId();

			List<Beverages> lu = (List<Beverages>) beveragesDao.findAll();

			for (int i = 0; i < lu.size(); i++) {
				if (lu.get(i).getRestaurantId().getRestaurantId().equals(restaurant.getRestaurantId()))
					getRestaurantBeverage.add(lu.get(i));
			}

			return getRestaurantBeverage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/restaurantReservation", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String restaurantReservation(@RequestBody TableReservationPom tableReservationPom,
			HttpServletRequest request) {

		try {
			User user = (User) request.getSession().getAttribute("user");
			Restaurant restaurant = restaurantDao
					.findByRestaurantId(Long.parseLong(tableReservationPom.getRestaurantId()));
			DiningTable diningTable = dinningTableDao
					.findByGeneralTableID(Long.parseLong(tableReservationPom.getDiningTableId()));

			TableReservation tableReservation = new TableReservation(restaurant, tableReservationPom.getDate(),
					tableReservationPom.getTime(), Integer.parseInt(tableReservationPom.getHours()), diningTable, user);
			tableReservationDao.save(tableReservation);

			System.out.println("ISPIS DATUM..." + tableReservationPom.getDate());
			System.out.println("ISPIS VRIJEME..." + tableReservationPom.getTime() + ":00");
			String startDateString = tableReservationPom.getDate();
			String timeString = tableReservationPom.getTime();
			java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(startDateString + " " + timeString + ":00");
			System.out.println(timestamp.toString());

			int sec = 900; // 15 minuta
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timestamp.getTime());
			cal.add(Calendar.SECOND, -sec);
			Timestamp later = new Timestamp(cal.getTime().getTime());
			System.out.println("OVO JE POMJERENO VRIJEME" + later);

			GuestsOrder guestsOrder = new GuestsOrder(restaurant, null, user, diningTable, false, tableReservation,
					later);
			guestsOrderDao.save(guestsOrder);

			// tu napraviti inviteFriend za onoga ko pravi rezervaciju
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "OK";
	}

	@RequestMapping(value = "/sendInvite", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String sendInvite(@RequestBody InviteFriendPom inviteFriendPom, HttpServletRequest request) {

		try {
			User user = userDao.findByEmail(inviteFriendPom.getEmail());
			String id = inviteFriendPom.getTableReservationId();
			TableReservation tableReservation = tableReservationDao.findByTableReservationId(Integer.parseInt(id));

			InviteFriend inviteFriend = new InviteFriend(tableReservation, user, false);
			String startDateString = tableReservation.getDate();
			String timeString = tableReservation.getTime();
			java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(startDateString + " " + timeString + ":00");
			int sec = 900; // 15 minuta
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timestamp.getTime());
			cal.add(Calendar.SECOND, -sec);
			Timestamp later = new Timestamp(cal.getTime().getTime());
			System.out.println("OVO JE POMJERENO VRIJEME" + later);

			// GuestsOrder guestsOrder = new
			// GuestsOrder(tableReservation.getRestaurantId(), null, user,
			// tableReservation.getDiningTableId(), false, tableReservation,
			// later);
			// guestsOrderDao.save(guestsOrder);

			inviteFriendDao.save(inviteFriend);
			sendMail(tableReservation, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "OK";
	}

	/////////// nastaviti tu
	@RequestMapping(value = "/sendOrder", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String sendOrder(@RequestBody InviteFriendPom inviteFriendPom, HttpServletRequest request) {

		try {
			User user = (User) request.getSession().getAttribute("user");
			String id = inviteFriendPom.getTableReservationId();
			GuestsOrder guestsOrder = null;
			TableReservation tableReservation = tableReservationDao.findByTableReservationId(Integer.parseInt(id));
			List<GuestsOrder> lu = (List<GuestsOrder>) guestsOrderDao.findAll();
			for (int i = 0; i < lu.size(); i++) {
				if (lu.get(i).getGuest().getEmail().equals(user.getEmail()) && lu.get(i).getTableReservation()
						.getTableReservationId().equals(tableReservation.getTableReservationId())) {
					guestsOrder = lu.get(i);
				}
			}
			Menu menu = menuDao.findByMenuMealId(Long.parseLong(inviteFriendPom.getMenuId()));
			Beverages beverage = beveragesDao.findByBeveragesId(Long.parseLong(inviteFriendPom.getBeverageId()));

			OrderedMeal orderedMeal = new OrderedMeal(guestsOrder, null, menu, 1, null, null, null, null, null);
			OrderedBeverage orderedBeverage = new OrderedBeverage(guestsOrder, null, beverage, 1, null, null, null,
					null, null);

			orderedMealDao.save(orderedMeal);
			orderedBeverageDao.save(orderedBeverage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "OK";
	}

	// --------getAllRestaurants
	@RequestMapping(value = "/getAllRestaurants", method = RequestMethod.GET)
	public ArrayList<Restaurant> getAllRestaurants(HttpServletRequest request) {

		ArrayList<Restaurant> fs = (ArrayList<Restaurant>) restaurantDao.findAll();
		return fs;
	}

	// --------getOneRestaurant
	@RequestMapping(value = "/getOneRestaurant", method = RequestMethod.GET)
	public Restaurant getOneRestaurant(HttpServletRequest request) {

		RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
		Restaurant restaurant = rmkkk.getRestaurantId();

		// ArrayList<Restaurant> fs = (ArrayList<Restaurant>)
		// restaurantDao.findOne(null);
		return restaurant;
	}

	public void sendMail(TableReservation tableReservation, User user) throws MessagingException {
		StringBuffer sb = new StringBuffer();
		sb.append("<HTML><BODY style='border-width:0px'>");
		sb.append("<br><br>");
		sb.append("Postovanje,");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("Prijatelj " + tableReservation.getUserId().getUser_name() + " vas je pozvao u restoran.<br>");
		sb.append("Datum i vrijeme rezervacije su: " + tableReservation.getDate() + " " + tableReservation.getTime()
				+ "<br>");
		sb.append("Klikom na link ispod, potvrdite dolazak ili ignorisite zahtjev.");
		sb.append("<br>");
		sb.append("<br>");

		sb.append("<a href=\"http://localhost:8080/tableReservation/confirmTableReservation/"
				+ tableReservation.getTableReservationId() + "yz" + user.getEmail().trim() + "\">Confirm link</a>");
		sb.append("<BR/>");
		sb.append("</BODY></HTML>");
		smtpMailSender.send(user.getEmail().trim(), "Confirm table reservation from Spring", sb.toString());

	}

	@RequestMapping("/confirmTableReservation/{mail}")
	public String sendMail(@PathVariable("mail") String mail) throws MessagingException {
		try {

			System.out.println("STIGAO JE MAIL SA LINKOM: " + mail);
			String[] token = mail.split("yz");
			String uEmail = token[1] + ".com";
			String uTableResId = token[0];

			System.out.println("STIGAO JE MAIL U KONZOLU: " + uEmail);
			System.out.println("STIGAO JE TableResID U KONZOLU: " + uTableResId);

			List<InviteFriend> tr = (List<InviteFriend>) inviteFriendDao.findAll();
			for (int i = 0; i < tr.size(); i++) {
				if (tr.get(i).getTableReservationId().getTableReservationId()
						.equals(Integer.parseInt(uTableResId.trim()))) {
					if (tr.get(i).getUserId().getEmail().trim().equals(uEmail)) {
						tr1 = tr.get(i);
						tr1.setAccepted(true);
						inviteFriendDao.save(tr1);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Uspjesno prihvacen poziv za sto.";
	}

}
