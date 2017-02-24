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
import netgloo.dao.BeveragesDao;
import netgloo.dao.DinningTableDao;
import netgloo.dao.GuestsOrderDao;
import netgloo.dao.MenuDao;
import netgloo.dao.OrderedBeverageDao;
import netgloo.dao.OrderedMealDao;
import netgloo.models.Area;
import netgloo.models.AreaImmitation;
import netgloo.models.Beverages;
import netgloo.models.DiningTable;
import netgloo.models.Employee;
import netgloo.models.GuestsOrder;
import netgloo.models.Menu;
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
	
	@Autowired
	GuestsOrderDao goDao;
	
	@Autowired
	OrderedMealDao ordMealDao;
	
	@Autowired
	OrderedBeverageDao ordBevDao;
	
	@Autowired
	BeveragesDao bevDao;
	
	@Autowired
	MenuDao menuDao;
	
	private ArrayList<AreaImmitation> areasList = new ArrayList<AreaImmitation>();
	
	
	@RequestMapping(value = "/occupyTable", method = RequestMethod.POST)
	public void  occupyTable(@RequestBody TablePrint  tp, HttpServletRequest request)
	{
		//GuestsOrder go = new GuestsOrder();
		Long idStola  = tp.generalTableID;
		DiningTable dtable = dtDao.findOne(idStola);
		dtable.setOccupied(true);
		System.out.println(dtable.getGeneralTableID());
		dtDao.save(dtable);
		
		Employee emp = (Employee) request.getSession().getAttribute("employee");
		Timestamp eTime = null;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(Calendar.getInstance().getTime());
		//cal1.add(Calendar.DAY_OF_MONTH, -1);
		eTime = new Timestamp(cal1.getTime().getTime());
		
		GuestsOrder go = new GuestsOrder(emp.getRestaurantId(),emp,dtable, false,eTime);
		goDao.save(go);
		
		/*
		GuestsOrder lastGuOrd = null;
		int maxID =0;
		
		
		ArrayList<GuestsOrder> guOrList = goDao.findAllByDiningTable(dtable);
		
		for(GuestsOrder guo : guOrList)
		{
			if(guo.getOrderID()>maxID)
			{
				maxID= guo.getOrderID();
				lastGuOrd = guo;
			}
		}
		*/
		//System.out.println("DIN T: "+ lastGuOrd.getDiningTable().getGeneralTableID());
		//System.out.println("ORD: " + lastGuOrd.getOrderID());
		
		//return "ok";
	}
	
	
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
	
	
	@RequestMapping(value = "/getAllRestaurantBeverages", method = RequestMethod.POST)
	public ArrayList<Beverages> getAllRestaurantBeverages(HttpServletRequest request) {
			Employee emp = (Employee) request.getSession().getAttribute("employee");
			ArrayList<Beverages> bevList = bevDao.findAllByRestaurantId(emp.getRestaurant_id());
		return bevList;
	}
	
	@RequestMapping(value = "/getAllRestaurantMeals", method = RequestMethod.POST)
	public ArrayList<Menu> getAllRestaurantMeals(HttpServletRequest request) {
			Employee emp = (Employee) request.getSession().getAttribute("employee");
			ArrayList<Menu> menuList = menuDao.findAllByRestaurantId(emp.getRestaurant_id());
		return menuList;
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
			tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID()/*, dt.getGuestsOrder().getOrderID()*/));
		}
		
		for(DiningTable dt: dtList)
		{
			System.out.println("Table: " + dt.getPositionX()+", "+dt.getPositionY()+ " -> area: "+dt.getArea());
		}
		
		return tp;
	}
	
	@RequestMapping(value = "/seeAllTableOrders", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ArrayList<OrderImmitation> seeAllTableOrders(@RequestBody TablePrint tp, HttpServletRequest request) {
		
		ArrayList<OrderImmitation> oiList = new ArrayList<OrderImmitation>();
		
		Long idStola  = tp.generalTableID;
		DiningTable dtable = dtDao.findOne(idStola);
		
		
		
		GuestsOrder lastGuOrd = null;
		int maxID =0;
		
		
		ArrayList<GuestsOrder> guOrList = goDao.findAllByDiningTable(dtable);
		
		for(GuestsOrder guo : guOrList)
		{
			if(guo.getOrderID()>maxID)
			{
				maxID= guo.getOrderID();
				lastGuOrd = guo;
			}
		}
		
		ArrayList<OrderedMeal> ordMealList = ordMealDao.findAllByGuestsOrder(lastGuOrd);
		ArrayList<OrderedBeverage> ordBevList = ordBevDao.findAllByGuestsOrder(lastGuOrd);
		
		for(OrderedMeal om : ordMealList)
		{
			OrderImmitation ox =  new OrderImmitation(om.getOrderedMealID(),om.getMenu().getMenu_meal_description(), om.getQuantity(), om.getOrderedMealNote(), "","","",idStola,"m");
			ox.setNovitet("no");
			ox.setGuestOrderID(om.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		for(OrderedBeverage ob : ordBevList)
		{
			OrderImmitation ox =  new OrderImmitation(ob.getOrderedBeverageID(),ob.getBeverage().getBeveragesName(), ob.getQuantity(), ob.getOrderedBeverageNote(), "","","",idStola,"b");
			ox.setNovitet("no");
			ox.setGuestOrderID(ob.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		
		if(oiList.isEmpty())
		{
			OrderImmitation ox = new OrderImmitation();
			ox.setGuestOrderID(lastGuOrd.getOrderID());
			oiList.add(ox);
		}
		
		
		return oiList;
	}
	
	@RequestMapping(value = "/removeSomeOrder", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ArrayList<OrderImmitation> removeSomeOrder(@RequestBody OrderImmitation orderImmitationThing, HttpServletRequest request) {
		
		ArrayList<OrderImmitation> oiList = new ArrayList<OrderImmitation>();
		
		
		
		Long idStola  = orderImmitationThing.getTable();
		DiningTable dtable = dtDao.findOne(idStola);
		
		String whatIsIt = orderImmitationThing.getWhatIsIt();
		
		int idObroka = orderImmitationThing.getOrderId();
		
		if(whatIsIt.equals("m"))
		{
			OrderedMeal omel = ordMealDao.findOne(idObroka);
			ordMealDao.delete(omel);
			System.out.println("Obrisano jelo");
		}
		else if(whatIsIt.equals("b"))
		{
			OrderedBeverage obel = ordBevDao.findOne(idObroka);
			ordBevDao.delete(obel);
			System.out.println("Obrisano pice");
		}
		
		
		
		
		
		GuestsOrder lastGuOrd = null;
		int maxID =0;
		
		
		ArrayList<GuestsOrder> guOrList = goDao.findAllByDiningTable(dtable);
		
		for(GuestsOrder guo : guOrList)
		{
			if(guo.getOrderID()>maxID)
			{
				maxID= guo.getOrderID();
				lastGuOrd = guo;
			}
		}
		
		ArrayList<OrderedMeal> ordMealList = ordMealDao.findAllByGuestsOrder(lastGuOrd);
		ArrayList<OrderedBeverage> ordBevList = ordBevDao.findAllByGuestsOrder(lastGuOrd);
		
		for(OrderedMeal om : ordMealList)
		{
			OrderImmitation ox =  new OrderImmitation(om.getOrderedMealID(),om.getMenu().getMenu_meal_description(), om.getQuantity(), om.getOrderedMealNote(), "","","",idStola,"m");
			ox.setNovitet("no");
			ox.setGuestOrderID(om.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		for(OrderedBeverage ob : ordBevList)
		{
			OrderImmitation ox =  new OrderImmitation(ob.getOrderedBeverageID(),ob.getBeverage().getBeveragesName(), ob.getQuantity(), ob.getOrderedBeverageNote(), "","","",idStola,"b");
			ox.setNovitet("no");
			ox.setGuestOrderID(ob.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		
		if(oiList.isEmpty())
		{
			OrderImmitation ox = new OrderImmitation();
			ox.setGuestOrderID(lastGuOrd.getOrderID());
			oiList.add(ox);
		}
		
		
		return oiList;
	}
	
	
	@RequestMapping(value = "/addNewSomeOrder", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ArrayList<OrderImmitation> addNewSomeOrder(@RequestBody OrderImmitation orderImmitationThing, HttpServletRequest request) {
		
		
		System.out.println("STIGLO U SERVER ..... valjda");
		ArrayList<OrderImmitation> oiList = new ArrayList<OrderImmitation>();
		
		
		
		//Long idStola  = orderImmitationThing.getTable();
		//DiningTable dtable = dtDao.findOne(idStola);
		
		String whatIsIt = orderImmitationThing.getWhatIsIt();
		
		//int idObroka = orderImmitationThing.getOrderId();
		GuestsOrder lastGuOrd = goDao.findOne(orderImmitationThing.getGuestOrderID());
		DiningTable dtable = lastGuOrd.getDiningTable();
		Long idStola = dtable.getGeneralTableID();
		
		if(whatIsIt.equals("m"))
		{
			//OrderedMeal omel = ordMealDao.findOne(idObroka);
			//ordMealDao.delete(omel);
			//System.out.println("Obrisano jelo");
			
			Menu menue = menuDao.findOne(orderImmitationThing.getWhatWasOrderedId());
			
			OrderedMeal omel = new OrderedMeal(lastGuOrd,menue, orderImmitationThing.getQuantity(),orderImmitationThing.getNote());
			ordMealDao.save(omel);
		}
		else if(whatIsIt.equals("b"))
		{
			//OrderedBeverage obel = ordBevDao.findOne(idObroka);
			//ordBevDao.delete(obel);
			//System.out.println("Obrisano pice");
			
			Beverages bev = bevDao.findOne(orderImmitationThing.getWhatWasOrderedId());
			OrderedBeverage obeg = new OrderedBeverage(lastGuOrd, bev,orderImmitationThing.getQuantity(),orderImmitationThing.getNote() );
			ordBevDao.save(obeg);
		}
		
		
		
		
		/*
		GuestsOrder lastGuOrd = null;
		int maxID =0;
		*/
		/*
		ArrayList<GuestsOrder> guOrList = goDao.findAllByDiningTable(dtable);
		
		for(GuestsOrder guo : guOrList)
		{
			if(guo.getOrderID()>maxID)
			{
				maxID= guo.getOrderID();
				lastGuOrd = guo;
			}
		}
		*/
		ArrayList<OrderedMeal> ordMealList = ordMealDao.findAllByGuestsOrder(lastGuOrd);
		ArrayList<OrderedBeverage> ordBevList = ordBevDao.findAllByGuestsOrder(lastGuOrd);
		
		for(OrderedMeal om : ordMealList)
		{
			OrderImmitation ox =  new OrderImmitation(om.getOrderedMealID(),om.getMenu().getMenu_meal_description(), om.getQuantity(), om.getOrderedMealNote(), "","","",idStola,"m");
			ox.setNovitet("no");
			ox.setGuestOrderID(om.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		for(OrderedBeverage ob : ordBevList)
		{
			OrderImmitation ox =  new OrderImmitation(ob.getOrderedBeverageID(),ob.getBeverage().getBeveragesName(), ob.getQuantity(), ob.getOrderedBeverageNote(), "","","",idStola,"b");
			ox.setNovitet("no");
			ox.setGuestOrderID(ob.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		
		if(oiList.isEmpty())
		{
			OrderImmitation ox = new OrderImmitation();
			ox.setGuestOrderID(lastGuOrd.getOrderID());
			oiList.add(ox);
		}
		
		
		return oiList;
	}
	
	/*
	@RequestMapping(value = "/createBill", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public BillImmitation createBill(@RequestBody OrderImmitation orderImmitationThing, HttpServletRequest request) {
		
		ArrayList<OrderImmitation> oiList = new ArrayList<OrderImmitation>();
		
		
		
		Long idStola  = orderImmitationThing.getTable();
		DiningTable dtable = dtDao.findOne(idStola);
		
		String whatIsIt = orderImmitationThing.getWhatIsIt();
		
		int idObroka = orderImmitationThing.getOrderId();
		
		if(whatIsIt.equals("m"))
		{
			OrderedMeal omel = ordMealDao.findOne(idObroka);
			ordMealDao.delete(omel);
			System.out.println("Obrisano jelo");
		}
		else if(whatIsIt.equals("b"))
		{
			OrderedBeverage obel = ordBevDao.findOne(idObroka);
			ordBevDao.delete(obel);
			System.out.println("Obrisano pice");
		}
		
		
		
		
		
		GuestsOrder lastGuOrd = null;
		int maxID =0;
		
		
		ArrayList<GuestsOrder> guOrList = goDao.findAllByDiningTable(dtable);
		
		for(GuestsOrder guo : guOrList)
		{
			if(guo.getOrderID()>maxID)
			{
				maxID= guo.getOrderID();
				lastGuOrd = guo;
			}
		}
		
		ArrayList<OrderedMeal> ordMealList = ordMealDao.findAllByGuestsOrder(lastGuOrd);
		ArrayList<OrderedBeverage> ordBevList = ordBevDao.findAllByGuestsOrder(lastGuOrd);
		
		for(OrderedMeal om : ordMealList)
		{
			OrderImmitation ox =  new OrderImmitation(om.getOrderedMealID(),om.getMenu().getMenu_meal_description(), om.getQuantity(), om.getOrderedMealNote(), "","","",idStola,"m");
			ox.setNovitet("no");
			ox.setGuestOrderID(om.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		for(OrderedBeverage ob : ordBevList)
		{
			OrderImmitation ox =  new OrderImmitation(ob.getOrderedBeverageID(),ob.getBeverage().getBeveragesName(), ob.getQuantity(), ob.getOrderedBeverageNote(), "","","",idStola,"b");
			ox.setNovitet("no");
			ox.setGuestOrderID(ob.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		
		if(oiList.isEmpty())
		{
			OrderImmitation ox = new OrderImmitation();
			ox.setGuestOrderID(lastGuOrd.getOrderID());
			oiList.add(ox);
		}
		
		
		return oiList;
	}
	*/
}
