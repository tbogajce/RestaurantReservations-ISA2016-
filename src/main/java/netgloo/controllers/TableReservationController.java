package netgloo.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.DinningTableDao;
import netgloo.dao.FriendshipsDao;
import netgloo.dao.GuestsOrderDao;
import netgloo.dao.InviteFriendDao;
import netgloo.dao.RestaurantDao;
import netgloo.dao.TableReservationDao;
import netgloo.dao.UserDao;
import netgloo.models.DiningTable;
import netgloo.models.Friendships;
import netgloo.models.GuestsOrder;
import netgloo.models.InviteFriend;
import netgloo.models.InviteFriendPom;
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
	private UserDao userDao;

	@Autowired
	private SmtpMailSender smtpMailSender;

	@Autowired
	private FriendshipsDao friendshipsDao;

	@Autowired
	private GuestsOrderDao guestsOrderDao;

	public ArrayList<Restaurant> restaurantCombo = new ArrayList<Restaurant>();
	public ArrayList<DiningTable> diningTableList = new ArrayList<DiningTable>();
	public ArrayList<TableReservation> reservedRestaurant = new ArrayList<TableReservation>();
	public ArrayList<User> allFriends = new ArrayList<User>();
	public InviteFriend tr1 = null;

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

	@RequestMapping(value = "/getReservedRestaurantData", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public TableReservation getReservedRestaurantData(@RequestBody TableReservationPom tableReservationPom,
			HttpServletRequest request) {
		String tableResId = tableReservationPom.getTableReservationId();
		TableReservation tableReservation = tableReservationDao.findByTableReservationId(Integer.parseInt(tableResId));

		return tableReservation;
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
			inviteFriendDao.save(inviteFriend);
			sendMail(tableReservation, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "OK";
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
