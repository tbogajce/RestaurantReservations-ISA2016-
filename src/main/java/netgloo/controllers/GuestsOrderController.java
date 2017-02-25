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

import netgloo.dao.GuestsOrderDao;
import netgloo.dao.OrderedBeverageDao;
import netgloo.dao.OrderedMealDao;
import netgloo.models.Employee;
import netgloo.models.OrderImmitation;
import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;
import netgloo.models.SystemManager;
import netgloo.models.User;
import netgloo.models.UserProba;

@RestController
@RequestMapping("/guestsOrderController")
public class GuestsOrderController {
	
	private ArrayList<OrderImmitation> orderImitsList = new ArrayList<OrderImmitation>();
	
	@Autowired
	GuestsOrderDao guOrdDao;
	
	@Autowired 
	OrderedBeverageDao obDao;
	
	@Autowired
	OrderedMealDao omDao;
	
	
	@RequestMapping(value = "/acceptedOrder", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String acceptedOrder(@RequestBody OrderImmitation oi, HttpServletRequest request) {

		try {
			System.out.println("OVO JE OI: " + oi.getOrderId());
			
			Employee emp = (Employee) request.getSession().getAttribute("employee");
			
			if(emp.getEmployeeRole().equals("Bartender"))
			{
				OrderedBeverage orderedBeverage = obDao.findOne(oi.getOrderId());
				
				Timestamp sTime = null;
				Calendar cal = Calendar.getInstance();
				cal.setTime(Calendar.getInstance().getTime());
				//cal.add(Calendar.DAY_OF_MONTH, -1);
				sTime = new Timestamp(cal.getTime().getTime());
				
				orderedBeverage.setAcceptedTime(sTime);
				orderedBeverage.setIsAccepted(true);
				orderedBeverage.setBartender(emp);
				obDao.save(orderedBeverage);
				
			}
			if(emp.getEmployeeRole().equals("Cook"))
			{
				OrderedMeal orderedMeal = omDao.findOne(oi.getOrderId());
				Timestamp sTime = null;
				Calendar cal = Calendar.getInstance();
				cal.setTime(Calendar.getInstance().getTime());
				//cal.add(Calendar.DAY_OF_MONTH, -1);
				sTime = new Timestamp(cal.getTime().getTime());
				
				orderedMeal.setIsAccepted(true);
				orderedMeal.setAcceptedTime(sTime);
				orderedMeal.setCook(emp);
				omDao.save(orderedMeal);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//return "EXCEPTION";
		}
		return "OK";
	}
	
	@RequestMapping(value = "/canOrder", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String canOrder(@RequestBody OrderImmitation oi, HttpServletRequest request) {

		try {
			System.out.println("OVO JE OI: " + oi.getOrderId());
			
			Employee emp = (Employee) request.getSession().getAttribute("employee");
			
			if(emp.getEmployeeRole().equals("Bartender"))
			{
				OrderedBeverage orderedBeverage = obDao.findOne(oi.getOrderId());
				/*
				Timestamp sTime = null;
				Calendar cal = Calendar.getInstance();
				cal.setTime(Calendar.getInstance().getTime());
				//cal.add(Calendar.DAY_OF_MONTH, -1);
				sTime = new Timestamp(cal.getTime().getTime());
				
				orderedBeverage.setAcceptedTime(sTime);
				orderedBeverage.setIsAccepted(true);
				orderedBeverage.setBartender(emp);
				*/
				orderedBeverage.setIsCanceled(true);
				obDao.save(orderedBeverage);
				
			}
			if(emp.getEmployeeRole().equals("Cook"))
			{
				OrderedMeal orderedMeal = omDao.findOne(oi.getOrderId());
				/*
				Timestamp sTime = null;
				Calendar cal = Calendar.getInstance();
				cal.setTime(Calendar.getInstance().getTime());
				//cal.add(Calendar.DAY_OF_MONTH, -1);
				sTime = new Timestamp(cal.getTime().getTime());
				
				orderedMeal.setIsAccepted(true);
				orderedMeal.setAcceptedTime(sTime);
				orderedMeal.setCook(emp);
				*/
				orderedMeal.setIsCanceled(true);
				omDao.save(orderedMeal);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//return "EXCEPTION";
		}
		return "OK";
	}
	
	
	@RequestMapping(value = "/doneOrder", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String doneOrder(@RequestBody OrderImmitation oi, HttpServletRequest request) {

		try {
			System.out.println("OVO JE OI: " + oi.getOrderId());
			
			Employee emp = (Employee) request.getSession().getAttribute("employee");
			
			if(emp.getEmployeeRole().equals("Bartender"))
			{
				OrderedBeverage orderedBeverage = obDao.findOne(oi.getOrderId());
				
				Timestamp sTime = null;
				Calendar cal = Calendar.getInstance();
				cal.setTime(Calendar.getInstance().getTime());
				//cal.add(Calendar.DAY_OF_MONTH, -1);
				sTime = new Timestamp(cal.getTime().getTime());
				
				orderedBeverage.setDoneTime(sTime);
				orderedBeverage.setIsDone(true);
				//orderedBeverage.setBartender(emp);
				obDao.save(orderedBeverage);
				
			}
			if(emp.getEmployeeRole().equals("Cook"))
			{
				OrderedMeal orderedMeal = omDao.findOne(oi.getOrderId());
				Timestamp sTime = null;
				Calendar cal = Calendar.getInstance();
				cal.setTime(Calendar.getInstance().getTime());
				//cal.add(Calendar.DAY_OF_MONTH, -1);
				sTime = new Timestamp(cal.getTime().getTime());
				
				orderedMeal.setIsDone(true);
				orderedMeal.setDoneTime(sTime);
				orderedMeal.setCook(emp);
				omDao.save(orderedMeal);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//return "EXCEPTION";
		}
		return "OK";
	}
	
	
	@RequestMapping(value = "/getOrders", method = RequestMethod.POST)
	public ArrayList<OrderImmitation> getOrders(HttpServletRequest request) {
		//friends.clear();
		orderImitsList.clear();
		
		try {
			//User user = (User) request.getSession().getAttribute("user");
			/*
			java.sql.Timestamp ts = ...
					Calendar cal = Calendar.getInstance();
					cal.setTime(ts);
					cal.add(Calendar.DAY_OF_WEEK, 14);
					ts.setTime(cal.getTime().getTime()); // or
					ts = new Timestamp(cal.getTime().getTime());
			*/
			
			Employee emp = (Employee) request.getSession().getAttribute("employee");
			
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
			
			//Timestamp sTime = Timestamp.valueOf("2016-12-18 00:00:00");
			//Timestamp sxTime = new Timestamp();
			
			//Timestamp eTime = new Timestamp(System.currentTimeMillis());
			//Timestamp eTime = Timestamp.valueOf("2016-12-20 22:01:00");
			
			if(emp.getEmployeeRole().equals("Bartender"))
			{
				ArrayList<OrderedBeverage> listac=new ArrayList<OrderedBeverage>();
				Pageable page= new PageRequest(0, 40);
				listac= guOrdDao.findByRestaurantAndDates(emp.getRestaurantId(), sTime, eTime,page);
				
				for(OrderedBeverage ob : listac)
				{
					String employeeString="";
					String acceptedTime="";
					String doneTime="";
					String note="";
					String canceled="";
					if(ob.getBartender()!=null)
					{
						employeeString = ob.getBartender().getUserId().getUserName()+" "+ob.getBartender().getUserId().getUserSurname();
					}
					if(ob.getAcceptedTime()!=null)
					{
						acceptedTime=ob.getAcceptedTime().toString();
					}
					if(ob.getDoneTime()!=null)
					{
						doneTime=ob.getDoneTime().toString();
					}
					if(ob.getOrderedBeverageNote()!=null && !ob.getOrderedBeverageNote().equals(""))
					{
						note=ob.getOrderedBeverageNote();
					}
					if(ob.getIsCanceled()==true)
					{
						canceled="canceled";
					}
					OrderImmitation oi = new OrderImmitation(ob.getOrderedBeverageID(),ob.getBeverage().getBeveragesName(),ob.getQuantity(),note,acceptedTime,doneTime,employeeString,ob.getGuestsOrder().getDiningTable().getTableNumberInRestaurant(),"b",canceled);
					orderImitsList.add(oi);
				}
			}
			
			if(emp.getEmployeeRole().equals("Cook"))
			{
				ArrayList<OrderedMeal> listac=new ArrayList<OrderedMeal>();
				Pageable page= new PageRequest(0, 40);
				listac= guOrdDao.findByRestaurantAndDatesMeal(emp.getRestaurantId(), sTime, eTime,page);
				
				for(OrderedMeal ob : listac)
				{
					String employeeString="";
					String acceptedTime="";
					String doneTime="";
					String note="";
					String canceled="";
					if(ob.getCook()!=null)
					{
						employeeString = ob.getCook().getUserId().getUserName()+" "+ob.getCook().getUserId().getUserSurname();
					}
					if(ob.getAcceptedTime()!=null)
					{
						acceptedTime=ob.getAcceptedTime().toString();
					}
					if(ob.getDoneTime()!=null)
					{
						doneTime=ob.getDoneTime().toString();
					}
					if(ob.getOrderedMealNote()!=null && !ob.getOrderedMealNote().equals(""))
					{
						note=ob.getOrderedMealNote();
					}
					if(ob.getIsCanceled()!=null && ob.getIsCanceled()==true)
					{
						canceled="canceled";
					}
					OrderImmitation oi = new OrderImmitation(ob.getOrderedMealID(),ob.getMenu().getMenuMealDescription(),ob.getQuantity(),note,acceptedTime,doneTime,employeeString,ob.getGuestsOrder().getDiningTable().getTableNumberInRestaurant(),"m",canceled);
					orderImitsList.add(oi);
				}
			}
			
			
			
			
			//Integer userID = user.getUser_id();
			//ArrayList<Friendships> fs = (ArrayList<Friendships>) friendshipsDao.findByLoveGiver(user);
			//for(int i=0; i<fs.size(); i++) {
			//	friends.add(fs.get(i).getLove_taker());
			//}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return orderImitsList;
	}
	

}
