package netgloo.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.OfferManagerDao;
import netgloo.models.DiningTable;
import netgloo.models.GuestsOrder;
import netgloo.models.OfferManager;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;
import netgloo.models.TableReservation;
import netgloo.models.TableReservationPom;
import netgloo.models.User;

@RestController
@RequestMapping("/offers")
public class Offers {

	@Autowired
	private OfferManagerDao offerManagerDao;

	@RequestMapping(value = "/getAllYourOffer", method = RequestMethod.GET)
	public ArrayList<OfferManager> getAllYourOffer(HttpServletRequest request) {
		ArrayList<OfferManager> fs = (ArrayList<OfferManager>) offerManagerDao.findAll();
		return fs;
	}

	@RequestMapping(value = "/createOfferManager", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createOfferManager(@RequestBody OfferManager offerManager,
			HttpServletRequest request) {
		try {
			RestaurantManager rm = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			String note = offerManager.getNote();
			String deadline = offerManager.getDeadline();
			OfferManager om = new OfferManager(rm, note, deadline);
			offerManagerDao.save(om);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "OK";
	}

}
