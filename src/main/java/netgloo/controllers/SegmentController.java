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
import netgloo.dao.SegmentDao;
import netgloo.models.Area;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;
import netgloo.models.Segment;

@RestController
@RequestMapping("/segmentController")
public class SegmentController {

	@Autowired
	private RestaurantDao restDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private SegmentDao segDao;

	ArrayList<Segment> listOfSegments = new ArrayList<Segment>();

	@RequestMapping(value = "/createNewSegment", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createSegment(@RequestBody Segment s1, HttpServletRequest request) {
		System.out.println("SEGMENT KRENUO");
		try {

			RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			Restaurant restaurant = rmkkk.getRestaurantId();
			Area area = areaDao.findByRestaurant(restaurant);
			System.out.println("USAO U KREIRANJE Segment");
			
			// Area svi = (Area)
			ArrayList<Segment> areasList = (java.util.ArrayList<Segment>) segDao.findAll();

			Long max = (long) 0;

			for (Segment a : areasList) {
				if (a.getSegmentID() > max) {
					max = a.getSegmentID();
				}
			}
			
			// String user_reg_date = new
			// SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			Segment segment = new Segment(max+1, restaurant, area, s1.getSegmentName(), s1.getSegmentSpace(), s1.getNote());
			System.out.println("DODAO OBJEKAT Segment");
			segDao.save(segment);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

}
