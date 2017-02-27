package netgloo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.BeveragesDao;
import netgloo.dao.EmployeeDao;
import netgloo.dao.HistoryRecordDao;
import netgloo.dao.MenuDao;
import netgloo.dao.OrderedBeverageDao;
import netgloo.dao.OrderedMealDao;
import netgloo.dao.RestaurantDao;
import netgloo.models.AreaImmitation;
import netgloo.models.Beverages;
import netgloo.models.Employee;
import netgloo.models.GuestsOrder;
import netgloo.models.HistoryRecord;
import netgloo.models.HistoryRecordImmitation;
import netgloo.models.Menu;
import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;
import netgloo.models.Restaurant;
import netgloo.models.TablePrint;
import netgloo.models.User;

@RestController
@RequestMapping("/ratingsController")
public class RatingsController {
	
	@Autowired
	HistoryRecordDao hrDao;
	
	@Autowired
	OrderedMealDao ordMealDao;
	
	@Autowired
	OrderedBeverageDao ordBevDao;
	
	@Autowired
	RestaurantDao resDao;
	
	@Autowired
	EmployeeDao empDao;
	
	@Autowired
	BeveragesDao bevDao;
	
	@Autowired
	MenuDao menDao;
	
	
	@RequestMapping(value = "/getHistoryRecords", method = RequestMethod.POST)
	public ArrayList<HistoryRecordImmitation>  getHistoryRecords(HttpServletRequest request)
	{
		
		User user = (User) request.getSession().getAttribute("user");
		
		ArrayList<HistoryRecord> hrList = hrDao.findAllByUser(user);
		
		ArrayList<HistoryRecordImmitation> hrImitList = new ArrayList<HistoryRecordImmitation>();
		
		for(HistoryRecord hr : hrList)
		{
			String datum = hr.getGuestsOrder().getTableReservation().getDate();
			
			ArrayList<OrderedBeverage> obList = ordBevDao.findAllByGuestsOrder(hr.getGuestsOrder());
			ArrayList<OrderedMeal> omList = ordMealDao.findAllByGuestsOrder(hr.getGuestsOrder());
			
			String pica="";
			String hrana="";
			
			for(OrderedBeverage ob : obList)
			{
				int q = ob.getQuantity();
				String naz = ob.getBeverage().getBeveragesName();
				pica=pica + naz + " x " + q + " ; ";
			}
			for(OrderedMeal om : omList)
			{
				int q = om.getQuantity();
				String naz = om.getMenu().getMenuMealDescription();
				hrana = hrana + naz + " x " + q + " ; ";
			}
			String imeRestorana = hr.getRestaurant().getRestaurantName();
			
			int or = (int) hr.getRestaurantGrade();
			int ou = (int) hr.getServiceGrade();
			int oh = (int) hr.getMealGrade();
			
			HistoryRecordImmitation hri = new HistoryRecordImmitation(hr.getHistoryRecordId(),imeRestorana, datum, hrana,pica,or,oh,ou);
			hrImitList.add(hri);
			
		}
		
		return hrImitList;
		
	}
	
	@RequestMapping(value = "/rateRestaurant", method = RequestMethod.POST)
	public void  rateRestaurant(@RequestBody HistoryRecordImmitation hisRecIm, HttpServletRequest request)
	{
		
		
		System.out.println("PA OVO KAO RADI NESTO..");
		System.out.println("HR: "+ hisRecIm.getHistoryRecordId());
		System.out.println("OC: "+ hisRecIm.getOcjenaRestorana());
		User user = (User) request.getSession().getAttribute("user");
		
		int ocjena = hisRecIm.getOcjenaRestorana();
		
		HistoryRecord hr = hrDao.findOne(hisRecIm.getHistoryRecordId());
		
		if(hr.getHasAlreadyGradedRestaurant()==false)
		{
			
			Restaurant restaurant = hr.getGuestsOrder().getRestaurant();
			hr.setHasAlreadyGradedRestaurant(true);
			
			int suma = restaurant.getSumOfVotes();
			suma=suma+ocjena;
			restaurant.setSumOfVotes(suma);
			
			int voters = restaurant.getTotalNumberOfVoters();
			voters = voters +1;
			restaurant.setTotalNumberOfVoters(voters);
			
			resDao.save(restaurant);
		}
		else
		{
			int staraOcjena= (int) hr.getRestaurantGrade();
			
			Restaurant restaurant = hr.getGuestsOrder().getRestaurant();
			
			int suma = restaurant.getSumOfVotes();
			suma=suma-staraOcjena;
			suma=suma+ocjena;
			
			restaurant.setSumOfVotes(suma);
			
			resDao.save(restaurant);
			
		}
		
		hr.setRestaurantGrade((float) ocjena);
		hrDao.save(hr);
		
	}
	
	@RequestMapping(value = "/rateService", method = RequestMethod.POST)
	public void  rateService(@RequestBody HistoryRecordImmitation hisRecIm, HttpServletRequest request)
	{
		
		
		System.out.println("PA OVO KAO RADI NESTO..");
		System.out.println("HR: "+ hisRecIm.getHistoryRecordId());
		System.out.println("OC: "+ hisRecIm.getOcjenaRestorana());
		User user = (User) request.getSession().getAttribute("user");
		
		int ocjena = hisRecIm.getOcjenaRestorana();
		
		HistoryRecord hr = hrDao.findOne(hisRecIm.getHistoryRecordId());
		
		if(hr.getHasAlreadyGradedService()==false)
		{
			Employee konobar = hr.getGuestsOrder().getWaiter();
			int suma = konobar.getSumOfVotes();
			suma=suma+ocjena;
			konobar.setSumOfVotes(suma);
			int voters = konobar.getTotalNumberOfVoters();
			voters = voters +1;
			konobar.setTotalNumberOfVoters(voters);
			empDao.save(konobar);
		}
		else
		{
			int staraOcjena= (int) hr.getRestaurantGrade();
			Employee konobar = hr.getGuestsOrder().getWaiter();
			int suma = konobar.getSumOfVotes();
			suma=suma-staraOcjena;
			suma=suma+ocjena;
			konobar.setSumOfVotes(suma);
			empDao.save(konobar);
		}
		
		ArrayList<OrderedBeverage> obList = ordBevDao.findAllByGuestsOrder(hr.getGuestsOrder());
		ArrayList<OrderedMeal> omList = ordMealDao.findAllByGuestsOrder(hr.getGuestsOrder());
		
		for(OrderedBeverage ob : obList)
		{
			if(hr.getHasAlreadyGradedService()==false)
			{
				Employee konobar = ob.getBartender();
				int suma = konobar.getSumOfVotes();
				suma=suma+ocjena;
				konobar.setSumOfVotes(suma);
				int voters = konobar.getTotalNumberOfVoters();
				voters = voters +1;
				konobar.setTotalNumberOfVoters(voters);
				empDao.save(konobar);
			}
			else
			{
				int staraOcjena= (int) hr.getServiceGrade();
				Employee konobar = ob.getBartender();
				int suma = konobar.getSumOfVotes();
				suma=suma-staraOcjena;
				suma=suma+ocjena;
				konobar.setSumOfVotes(suma);
				empDao.save(konobar);
			}
		}
		
		for(OrderedMeal om: omList)
		{
			if(hr.getHasAlreadyGradedService()==false)
			{
				Employee konobar = om.getCook();
				int suma = konobar.getSumOfVotes();
				suma=suma+ocjena;
				konobar.setSumOfVotes(suma);
				int voters = konobar.getTotalNumberOfVoters();
				voters = voters +1;
				konobar.setTotalNumberOfVoters(voters);
				empDao.save(konobar);
			}
			else
			{
				int staraOcjena= (int) hr.getServiceGrade();
				Employee konobar = om.getCook();
				int suma = konobar.getSumOfVotes();
				suma=suma-staraOcjena;
				suma=suma+ocjena;
				konobar.setSumOfVotes(suma);
				empDao.save(konobar);
			}
		}
		
		
		
		
		
		
		hr.setHasAlreadyGradedService(true);
		hr.setServiceGrade((float) ocjena);
		hrDao.save(hr);
		
	}
	
	@RequestMapping(value = "/rateMeal", method = RequestMethod.POST)
	public void  rateMeal(@RequestBody HistoryRecordImmitation hisRecIm, HttpServletRequest request)
	{
		System.out.println("PA OVO KAO RADI NESTO..");
		System.out.println("HR: "+ hisRecIm.getHistoryRecordId());
		System.out.println("OC: "+ hisRecIm.getOcjenaRestorana());
		User user = (User) request.getSession().getAttribute("user");
		
		int ocjena = hisRecIm.getOcjenaRestorana();
		
		HistoryRecord hr = hrDao.findOne(hisRecIm.getHistoryRecordId());
		
		
		
		ArrayList<OrderedBeverage> obList = ordBevDao.findAllByGuestsOrder(hr.getGuestsOrder());
		ArrayList<OrderedMeal> omList = ordMealDao.findAllByGuestsOrder(hr.getGuestsOrder());
		
		for(OrderedBeverage ob : obList)
		{
			if(hr.getHasAlreadyGradedMeal()==false)
			{
				Beverages konobar = ob.getBeverage();
				int suma = konobar.getSumOfVotes();
				suma=suma+ocjena;
				konobar.setSumOfVotes(suma);
				int voters = konobar.getTotalNumberOfVoters();
				voters = voters +1;
				konobar.setTotalNumberOfVoters(voters);
				bevDao.save(konobar);
			}
			else
			{
				int staraOcjena= (int) hr.getMealGrade();
				Beverages konobar = ob.getBeverage();
				int suma = konobar.getSumOfVotes();
				suma=suma-staraOcjena;
				suma=suma+ocjena;
				konobar.setSumOfVotes(suma);
				bevDao.save(konobar);
			}
		}
		
		for(OrderedMeal om: omList)
		{
			if(hr.getHasAlreadyGradedMeal()==false)
			{
				Menu konobar = om.getMenu();
				int suma = konobar.getSumOfVotes();
				suma=suma+ocjena;
				konobar.setSumOfVotes(suma);
				int voters = konobar.getTotalNumberOfVoters();
				voters = voters +1;
				konobar.setTotalNumberOfVoters(voters);
				menDao.save(konobar);
			}
			else
			{
				int staraOcjena= (int) hr.getMealGrade();
				Menu konobar = om.getMenu();
				int suma = konobar.getSumOfVotes();
				suma=suma-staraOcjena;
				suma=suma+ocjena;
				konobar.setSumOfVotes(suma);
				menDao.save(konobar);
			}
		}
		
		
		hr.setHasAlreadyGradedMeal(true);
		hr.setMealGrade((float) ocjena);
		hrDao.save(hr);
		
	}
	

}
