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
	public ArrayList<OfferProvider> offersOfProvider = new ArrayList<>();
	public ArrayList<OfferProvider> pendingProviders = new ArrayList<>();
	public ArrayList<OfferManager> tempList = new ArrayList<>();

	@RequestMapping(value = "/getAllYourOffer", method = RequestMethod.GET)
	public ArrayList<OfferManager> getAllYourOffer(HttpServletRequest request) {
		offersOfManager.clear();
		RestaurantManager rm = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
		ArrayList<OfferManager> fs = (ArrayList<OfferManager>) offerManagerDao.findAll();
		for (int i = 0; i < fs.size(); i++) {
			if (fs.get(i).getRestaurantManagerNickId().getRestaurantManagerNickId()
					.equals(rm.getRestaurantManagerNickId()))
				offersOfManager.add(fs.get(i));
		}
		return offersOfManager;
	}
	
	@RequestMapping(value = "/pendingOffers", method = RequestMethod.GET)
	public ArrayList<OfferProvider> pendingOffers(HttpServletRequest request) {
		pendingProviders.clear();
		RestaurantManager rm = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
		ArrayList<OfferProvider> fs = (ArrayList<OfferProvider>) offerProviderDao.findAll();
		
		for(int i=0; i< fs.size(); i++) {
			if(fs.get(i).getOfferManagerId().getRestaurantManagerNickId().getRestaurantManagerNickId().equals(rm.getRestaurantManagerNickId()))
				if(!fs.get(i).getIsAccepted())
					pendingProviders.add(fs.get(i));
		}

		return pendingProviders;
	}

	@RequestMapping(value = "/getAllOfferProvider", method = RequestMethod.GET)
	public ArrayList<OfferManager> getAllOfferProvider(HttpServletRequest request) {
		tempList.clear();
		ArrayList<OfferManager> fs = (ArrayList<OfferManager>) offerManagerDao.findAll();
		for(OfferManager om : fs) {
			if(!om.getFinished())
				tempList.add(om);
		}
		return tempList;
	}
	
	@RequestMapping(value = "/offerData", method = RequestMethod.GET)
	public ArrayList<OfferProvider> offerData(HttpServletRequest request) {
		offersOfProvider.clear();
		Provider provider = (Provider) request.getSession().getAttribute("provider");
		ArrayList<OfferProvider> fs = (ArrayList<OfferProvider>) offerProviderDao.findAll();
		for(int i=0; i<fs.size();i++) {
			if(fs.get(i).getProviderNickId().getProviderNickId().equals(provider.getProviderNickId()))
				offersOfProvider.add(fs.get(i));
		}
		
		return offersOfProvider;
	}

	@RequestMapping(value = "/createOfferManager", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createOfferManager(@RequestBody OfferManager offerManager, HttpServletRequest request) {
		try {
			RestaurantManager rm = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			String note = offerManager.getNote();
			String deadline = offerManager.getDeadline();
			OfferManager om = new OfferManager(rm, note, deadline, false);
			offerManagerDao.save(om);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "OK";
	}
	
	@RequestMapping(value = "/acceptOffer", method = RequestMethod.POST, headers = {
	"content-type=application/json" })
public String acceptOffer(@RequestBody OfferProviderPom offerProviderPom, HttpServletRequest request) {
try {
	String id = offerProviderPom.getOfferProviderId();
	System.out.println(offerProviderPom.getOfferProviderId() + "  OVO JE BIO STRING");
	
	OfferProvider offerProvider = offerProviderDao.findOne(Integer.parseInt(id));
	offerProvider.setIsAccepted(true);
	offerProviderDao.save(offerProvider);

} catch (Exception e) {
	e.printStackTrace();
}

return "OK";
}

	@RequestMapping(value = "/makeOfferProvider", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String makeOfferProvider(@RequestBody OfferProviderPom offerProviderPom, HttpServletRequest request) {
		try {
			String id = offerProviderPom.getOfferProviderId();// ovo je zapravo
																// id od
																// offerManager
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
