package netgloo.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.AreaDao;
import netgloo.dao.DinningTableDao;
import netgloo.models.Area;
import netgloo.models.AreaImmitation;
import netgloo.models.DiningTable;
import netgloo.models.Employee;
import netgloo.models.OrderImmitation;
import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;
import netgloo.models.TablePrint;


@RestController
@RequestMapping("/tablesAndBillController")
public class TablesAndBillController {

	
	@Autowired 
	DinningTableDao dtDao;
	
	@Autowired
	AreaDao aDao;
	
	private ArrayList<AreaImmitation> areasList = new ArrayList<AreaImmitation>();
	
	
	@RequestMapping(value = "/getAreas", method = RequestMethod.POST)
	public ArrayList<AreaImmitation> getAreas(HttpServletRequest request) {
		//friends.clear();
		areasList.clear();
		
		try {
			
			Employee emp = (Employee) request.getSession().getAttribute("employee");
			ArrayList<Area> al = new ArrayList<Area>();
			al = aDao.findAllByRestaurant(emp.getRestaurantId());
			
			for(Area a : al)
			{
				areasList.add(new AreaImmitation(a.getAreaName(),a.getRestaurant().getRestaurantName(),a.getAreaID(),a.getRestaurant().getRestaurantId()));
			}
			
			for(AreaImmitation a : areasList)
			{
				System.out.println("AREA: " + a.getAreaName() +" , - r: " + a.getRestaurantName());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return areasList;
	}
	
	@RequestMapping(value = "/getAreaTables", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ArrayList<TablePrint> getAreaTables(@RequestBody AreaImmitation ai, HttpServletRequest request) {
		
		ArrayList<DiningTable> dtList = new ArrayList<DiningTable>();
		ArrayList<TablePrint> tp = new ArrayList<TablePrint>();
		System.out.println("USLO JE U OVO OVDE ZA TABLE");
		System.out.println("SELECTED AREA: "+ ai.areaID);
		
		Area areax = aDao.findOne(ai.getAreaID());
		
		dtList = dtDao.findAllByArea(areax);
		
		for(DiningTable dt: dtList)
		{
			tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant()));
		}
		
		for(DiningTable dt: dtList)
		{
			System.out.println("Table: " + dt.getPositionX()+", "+dt.getPositionY()+ " -> area: "+dt.getArea());
		}
		
		return tp;
		
		
	}
	
}
