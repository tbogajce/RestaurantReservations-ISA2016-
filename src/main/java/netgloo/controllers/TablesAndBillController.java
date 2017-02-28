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
import netgloo.dao.BillDao;
import netgloo.dao.DinningTableDao;
import netgloo.dao.GuestsOrderDao;
import netgloo.dao.HistoryRecordDao;
import netgloo.dao.MenuDao;
import netgloo.dao.OrderedBeverageDao;
import netgloo.dao.OrderedMealDao;
import netgloo.dao.WaiterEarningsDao;
import netgloo.models.Area;
import netgloo.models.AreaImmitation;
import netgloo.models.Beverages;
import netgloo.models.Bill;
import netgloo.models.DiningTable;
import netgloo.models.Employee;
import netgloo.models.GuestsOrder;
import netgloo.models.HistoryRecord;
import netgloo.models.Menu;
import netgloo.models.OrderImmitation;
import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;
import netgloo.models.PriceToPay;
import netgloo.models.TablePrint;
import netgloo.models.TableReservation;
import netgloo.models.User;
import netgloo.models.WaiterEarnings;


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
	
	@Autowired
	BillDao billDao;
	
	@Autowired
	HistoryRecordDao historyRecDao;
	
	@Autowired
	WaiterEarningsDao waiterEarnDao;
	
	private ArrayList<AreaImmitation> areasList = new ArrayList<AreaImmitation>();
	
	
	@RequestMapping(value = "/occupyTable", method = RequestMethod.POST)
	public void  occupyTable(@RequestBody TablePrint  tp, HttpServletRequest request)
	{
		/*
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
		
		*/
		System.out.println("TP: "+tp.getGuestOrderID());
		GuestsOrder go = goDao.findOne(tp.getGuestOrderID());
		Employee emp = (Employee) request.getSession().getAttribute("employee");
		go.setWaiter(emp);
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
			if(emp!=null)
			{


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
			if(dt.isActive()==true)
			{
				if(dt.getCurrentGuestsOrder()!=null)
				{
					if(dt.getCurrentGuestsOrder().getWaiter()!=null)
					{
						tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),dt.getCurrentGuestsOrder().getWaiter().getEmployeeId(), dt.getCurrentGuestsOrder().getOrderID()));
					}
					else
					{
						tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),(long) -1, dt.getCurrentGuestsOrder().getOrderID()));
					}
				}
				else
				{
					tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),(long) -1, -1));

				}
			}
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
		
		
		
		GuestsOrder lastGuOrd = goDao.findOne(tp.getGuestOrderID());
		//int maxID =0;
		
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
			String status="Pending";
			if(om.getIsCanceled()!=null && om.getIsCanceled()==true)
			{
				status="Canceled";
			}
			if(om.getIsAccepted()!=null && om.getIsAccepted()==true)
			{
				status="Accepted";
			}
			if(om.getIsDone()!=null && om.getIsDone()==true)
			{
				status="Done";
			}
			
			OrderImmitation ox =  new OrderImmitation(om.getOrderedMealID(),om.getMenu().getMenuMealDescription(), om.getQuantity(), om.getOrderedMealNote(), "","","",idStola,"m","");
			ox.setNovitet("no");
			ox.setStatus(status);
			ox.setGuestOrderID(om.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		for(OrderedBeverage ob : ordBevList)
		{
			String status="Pending";
			if(ob.getIsCanceled()!=null && ob.getIsCanceled()==true)
			{
				status="Canceled";
			}
			if(ob.getIsAccepted()!=null && ob.getIsAccepted()==true)
			{
				status="Accepted";
			}
			if(ob.getIsDone()!=null && ob.getIsDone()==true)
			{
				status="Done";
			}
			OrderImmitation ox =  new OrderImmitation(ob.getOrderedBeverageID(),ob.getBeverage().getBeveragesName(), ob.getQuantity(), ob.getOrderedBeverageNote(), "","","",idStola,"b","");
			ox.setNovitet("no");
			ox.setStatus(status);
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
		
		
		
		
		
		GuestsOrder lastGuOrd = goDao.findOne(orderImmitationThing.getGuestOrderID());
		int maxID =0;
		
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
			
			String status="Pending";
			if(om.getIsCanceled()!=null && om.getIsCanceled()==true)
			{
				status="Canceled";
			}
			if(om.getIsAccepted()!=null && om.getIsAccepted()==true)
			{
				status="Accepted";
			}
			if(om.getIsDone()!=null && om.getIsDone()==true)
			{
				status="Done";
			}
			OrderImmitation ox =  new OrderImmitation(om.getOrderedMealID(),om.getMenu().getMenuMealDescription(), om.getQuantity(), om.getOrderedMealNote(), "","","",idStola,"m","");
			ox.setNovitet("no");
			ox.setStatus(status);
			ox.setGuestOrderID(om.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		for(OrderedBeverage ob : ordBevList)
		{
			String status="Pending";
			if(ob.getIsCanceled()!=null && ob.getIsCanceled()==true)
			{
				status="Canceled";
			}
			if(ob.getIsAccepted()!=null && ob.getIsAccepted()==true)
			{
				status="Accepted";
			}
			if(ob.getIsDone()!=null && ob.getIsDone()==true)
			{
				status="Done";
			}
			OrderImmitation ox =  new OrderImmitation(ob.getOrderedBeverageID(),ob.getBeverage().getBeveragesName(), ob.getQuantity(), ob.getOrderedBeverageNote(), "","","",idStola,"b","");
			ox.setNovitet("no");
			ox.setStatus(status);
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
			omel.setIsCanceled(false);
			ordMealDao.save(omel);
		}
		else if(whatIsIt.equals("b"))
		{
			//OrderedBeverage obel = ordBevDao.findOne(idObroka);
			//ordBevDao.delete(obel);
			//System.out.println("Obrisano pice");
			
			Beverages bev = bevDao.findOne(orderImmitationThing.getWhatWasOrderedId());
			OrderedBeverage obeg = new OrderedBeverage(lastGuOrd, bev,orderImmitationThing.getQuantity(),orderImmitationThing.getNote() );
			obeg.setIsCanceled(false);
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
			
			String status="Pending";
			if(om.getIsCanceled()!=null && om.getIsCanceled()==true)
			{
				status="Canceled";
			}
			if(om.getIsAccepted()!=null && om.getIsAccepted()==true)
			{
				status="Accepted";
			}
			if(om.getIsDone()!=null && om.getIsDone()==true)
			{
				status="Done";
			}
			OrderImmitation ox =  new OrderImmitation(om.getOrderedMealID(),om.getMenu().getMenuMealDescription(), om.getQuantity(), om.getOrderedMealNote(), "","","",idStola,"m","");
			ox.setNovitet("no");
			ox.setStatus(status);
			ox.setGuestOrderID(om.getGuestsOrder().getOrderID());
			oiList.add(ox);
		}
		for(OrderedBeverage ob : ordBevList)
		{
			String status="Pending";
			if(ob.getIsCanceled()!=null && ob.getIsCanceled()==true)
			{
				status="Canceled";
			}
			if(ob.getIsAccepted()!=null && ob.getIsAccepted()==true)
			{
				status="Accepted";
			}
			if(ob.getIsDone()!=null && ob.getIsDone()==true)
			{
				status="Done";
			}
			OrderImmitation ox =  new OrderImmitation(ob.getOrderedBeverageID(),ob.getBeverage().getBeveragesName(), ob.getQuantity(), ob.getOrderedBeverageNote(), "","","",idStola,"b","");
			ox.setNovitet("no");
			ox.setStatus(status);
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
	
	
	@RequestMapping(value = "/prepareBill", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public PriceToPay prepareBill(@RequestBody OrderImmitation orderImmitationThing, HttpServletRequest request) {
		
		
		float sumaSvega =0;

		GuestsOrder lastGuOrd = goDao.findOne(orderImmitationThing.getGuestOrderID());
		
		ArrayList<OrderedMeal> ordMealList = ordMealDao.findAllByGuestsOrder(lastGuOrd);
		ArrayList<OrderedBeverage> ordBevList = ordBevDao.findAllByGuestsOrder(lastGuOrd);
		
		for(OrderedMeal om : ordMealList)
		{
			int q = om.getQuantity();
			float pri = om.getMenu().getMenuMealPrice();
			sumaSvega = sumaSvega +((float) ((float)q*pri));
		}
		for(OrderedBeverage ob : ordBevList)
		{
			int q = ob.getQuantity();
			float pri = ob.getBeverage().getBeveragesPrice();
			sumaSvega = sumaSvega +((float) ((float)q*pri));
		}
		
		
		PriceToPay ptp = new PriceToPay(orderImmitationThing.getGuestOrderID(),sumaSvega);
		
		return ptp;
	}
	
	@RequestMapping(value = "/finishBill", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ArrayList<TablePrint> finishBill(@RequestBody OrderImmitation orderImmitationThing, HttpServletRequest request) {
		
		
		float sumaSvega =0;

		GuestsOrder lastGuOrd = goDao.findOne(orderImmitationThing.getGuestOrderID());
		
		Employee employee = lastGuOrd.getWaiter();
		
		DiningTable dinTab = lastGuOrd.getDiningTable();
		
		User guest = lastGuOrd.getGuest();
		
		ArrayList<OrderedMeal> ordMealList = ordMealDao.findAllByGuestsOrder(lastGuOrd);
		ArrayList<OrderedBeverage> ordBevList = ordBevDao.findAllByGuestsOrder(lastGuOrd);
		
		for(OrderedMeal om : ordMealList)
		{
			int q = om.getQuantity();
			float pri = om.getMenu().getMenuMealPrice();
			sumaSvega = sumaSvega +((float) ((float)q*pri));
		}
		for(OrderedBeverage ob : ordBevList)
		{
			int q = ob.getQuantity();
			float pri = ob.getBeverage().getBeveragesPrice();
			sumaSvega = sumaSvega +((float) ((float)q*pri));
		}
		
		lastGuOrd.setIsPaid(true);
		goDao.save(lastGuOrd);
		
		Bill bill = new Bill(lastGuOrd,sumaSvega);
		
		billDao.save(bill);
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		WaiterEarnings we = new WaiterEarnings(bill,date,sumaSvega,employee.getRestaurantId(),employee);
		
		waiterEarnDao.save(we);
		
		if(guest!=null)
		{
			HistoryRecord hr = new HistoryRecord(employee.getRestaurant_id(),lastGuOrd,guest,date);
			hr.setHasAlreadyGradedMeal(false);
			hr.setHasAlreadyGradedRestaurant(false);
			hr.setHasAlreadyGradedService(false);
			
			historyRecDao.save(hr);
		}
		
		dinTab.setOccupied(false);
		dinTab.setCurrentGuestsOrder(null);
		
		dtDao.save(dinTab);
		
		ArrayList<DiningTable> dtList = new ArrayList<DiningTable>();
		ArrayList<TablePrint> tp = new ArrayList<TablePrint>();
		//System.out.println("USLO JE U OVO OVDE ZA TABLE");
		//System.out.println("SELECTED AREA: "+ ai.areaID);
		
		Area areax = dinTab.getArea();
		
		dtList = dtDao.findAllByArea(areax);
		
		/*
		for(DiningTable dt: dtList)
		{
			
			//TableReservation tr = go.getTableReservation();
			//DiningTable dt = go.getDiningTable();
			
			//Timestamp goTime = go.getOrderReceivedTime();
			Timestamp curTime = null;
			Calendar calx = Calendar.getInstance();
			calx.setTime(Calendar.getInstance().getTime());
			//calx.add(Calendar.SECOND, -900);
			//cal.add(Calendar.DAY_OF_MONTH, -1);
			curTime = new Timestamp(calx.getTime().getTime());
			//curTime.setTime(curTime.);
			
			
			 
			
			Timestamp sTime = null;
			Calendar cal = Calendar.getInstance();
			cal.setTime(Calendar.getInstance().getTime());
			//cal.add(Calendar.DAY_OF_MONTH, -1);
			sTime = new Timestamp(cal.getTime().getTime());
			sTime.setHours(0);
			sTime.setMinutes(0);
			sTime.setSeconds(0);
			
			Timestamp eTime = null;
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(Calendar.getInstance().getTime());
			//cal1.add(Calendar.DAY_OF_MONTH, -1);
			eTime = new Timestamp(cal1.getTime().getTime());
			
			ArrayList<GuestsOrder> goTodayList = goDao.getGuestsOrderesForTimeIntervalAndSpecificTable(sTime, eTime, dt);
			
			GuestsOrder pravi=null;
			
			if(goTodayList!=null && goTodayList.isEmpty()==false)
			{
				for(GuestsOrder ggoo : goTodayList)
				{
					TableReservation tabRes = ggoo.getTableReservation();
					int trajanje = tabRes.getHours();
					Timestamp zakazano = ggoo.getOrderReceivedTime();
					
					Timestamp kasnije = null;
					//System.out.println(timestamp.toString());
					int sec = 3600*trajanje + 1800; // 15 minuta
					Calendar cal2 = Calendar.getInstance();
					//cal2.setTimeInMillis(timestamp.getTime());
					cal2.setTime(Calendar.getInstance().getTime());
					cal2.add(Calendar.SECOND, +sec);
					//Timestamp later = new Timestamp(cal2.getTime().getTime());
					kasnije = new Timestamp(cal2.getTime().getTime());
					//System.out.println("OVO JE POMJERENO VRIJEME" + later);
					
					if(curTime.after(zakazano) && kasnije.after(curTime) && ggoo.getIsPaid()!=true)
					{
						System.out.println("Uslo je u ovo ..... valjda");
						pravi=ggoo;
						//break;
					}
					
				}
			}
			
			System.out.println("-----------------+++----------------");
			if(pravi!=null)
			{
				Long waiterx = (long) -1;
				if(pravi.getWaiter()!=null)
				{
					waiterx = pravi.getWaiter().getEmployeeId();
				}
				
				System.out.println("STO: " + dt.getGeneralTableID()+ " GO: " + pravi.getOrderID() + " Waiter: " + waiterx);
				
				
				tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),waiterx,pravi.getOrderID()));

			}
			else
			{
				System.out.println("STO: " + dt.getGeneralTableID()+ " GO: null Waiter: " + -1);
				
				tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),(long)-1,-1));

			}

		}
		*/
		for(DiningTable dt: dtList)
		{

			if(dt.isActive()==true)
			{
				if(dt.getCurrentGuestsOrder()!=null)
				{
					if(dt.getCurrentGuestsOrder().getWaiter()!=null)
					{
						tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),dt.getCurrentGuestsOrder().getWaiter().getEmployeeId(), dt.getCurrentGuestsOrder().getOrderID()));
					}
					else
					{
						tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),(long) -1, dt.getCurrentGuestsOrder().getOrderID()));
					}
				}
				else
				{
					tp.add(new TablePrint(dt.getGeneralTableID(),dt.getOccupied(),dt.getPositionX(),dt.getPositionY(),areax.getSpaceX(),areax.getSpaceY(),dt.getTableNumberInRestaurant(),areax.getAreaID(),(long) -1, -1));

				}
			}
		}
		
		
		for(DiningTable dt: dtList)
		{
			System.out.println("Table: " + dt.getPositionX()+", "+dt.getPositionY()+ " -> area: "+dt.getArea());
		}
		
		return tp;
		//PriceToPay ptp = new PriceToPay(orderImmitationThing.getGuestOrderID(),sumaSvega);
		//return tp;
	}
	
}
