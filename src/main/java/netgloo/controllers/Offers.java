package netgloo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.OfferManagerDao;
import netgloo.dao.OfferProviderDao;
import netgloo.models.OfferManager;
import netgloo.models.OfferProvider;
import netgloo.models.OfferProviderPom;
import netgloo.models.Provider;
import netgloo.models.RestaurantManager;

@RestController
@RequestMapping("/offers")
public class Offers {

	@Autowired
	private OfferManagerDao offerManagerDao;
	
	@Autowired
	private OfferProviderDao offerProviderDao;
	
	public ArrayList<OfferManager> offersOfManager = new ArrayList<>();

	@RequestMapping(value = "/getAllYourOffer", method = RequestMethod.GET)
	public ArrayList<OfferManager> getAllYourOffer(HttpServletRequest request) {
		offersOfManager.clear();
		RestaurantManager rm = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
		ArrayList<OfferManager> fs = (ArrayList<OfferManager>) offerManagerDao.findAll();
		for(int i=0; i<fs.size();i++) {
			if(fs.get(i).getRestaurantManagerNickId().getRestaurantManagerNickId().equals(rm.getRestaurantManagerNickId()))
				offersOfManager.add(fs.get(i));
		}
		return offersOfManager;
	}
	
	@RequestMapping(value = "/getAllOfferProvider", method = RequestMethod.GET)
	public ArrayList<OfferManager> getAllOfferProvider(HttpServletRequest request) {
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
	
	@RequestMapping(value = "/makeOfferProvider", method = RequestMethod.POST, headers = {
	"content-type=application/json" })
public String makeOfferProvider(@RequestBody OfferProviderPom offerProviderPom,
	HttpServletRequest request) {
try {
	String id = offerProviderPom.getOfferProviderId();//ovo je zapravo id od offerManager
	OfferManager offerManager = offerManagerDao.findByOfferManagerId(Integer.parseInt(id));
	Provider provider = (Provider) request.getSession().getAttribute("provider");
	String note = offerProviderPom.getNote();
	String price = offerProviderPom.getPrice();
	
	OfferProvider offerProvider = new OfferProvider(provider, offerManager, price, note, false);
	offerProviderDao.save(offerProvider);

} catch (Exception e) {
	e.printStackTrace();
}

return "OK";
}

}
