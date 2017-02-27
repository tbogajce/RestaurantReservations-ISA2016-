package netgloo.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import netgloo.dao.DinningTableDao;
import netgloo.dao.GuestsOrderDao;
import netgloo.models.DiningTable;
import netgloo.models.GuestsOrder;
import netgloo.models.TableReservation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class ScheduledTasks {
	
	@Autowired
	GuestsOrderDao goDao;
	
	@Autowired
	DinningTableDao dinTabDao;

	
    //private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 55000)
    public void changeTablesToOccupied() {
       // log.info("The time is now {}", dateFormat.format(new Date()));
    	
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
    	
    	ArrayList<GuestsOrder> listaOrdera = goDao.getGuestsOrderesForToday(sTime, eTime);
    	
    	for (GuestsOrder go : listaOrdera)
    	{
    		if(go.getIsPaid()==false)
    		{
    			TableReservation tr = go.getTableReservation();
    			DiningTable dt = go.getDiningTable();

    			Timestamp goTime = go.getOrderReceivedTime();
    			Timestamp curTime = null;
    			Calendar calx = Calendar.getInstance();
    			calx.setTime(Calendar.getInstance().getTime());
    			//cal.add(Calendar.DAY_OF_MONTH, -1);
    			curTime = new Timestamp(calx.getTime().getTime());
    			//curTime.setTime(curTime.);

    			Timestamp petnesMinRanije = null;
    			//System.out.println(timestamp.toString());
    			int sec = 900; // 15 minuta
    			Calendar cal2 = Calendar.getInstance();
    			//cal2.setTimeInMillis(timestamp.getTime());
    			cal2.setTime(Calendar.getInstance().getTime());
    			cal2.add(Calendar.SECOND, -sec);
    			//Timestamp later = new Timestamp(cal2.getTime().getTime());
    			petnesMinRanije = new Timestamp(cal2.getTime().getTime());
    			//System.out.println("OVO JE POMJERENO VRIJEME" + later);

    			if(dt.getOccupied()==false && dt.getCurrentGuestsOrder()==null)
    			{
    				if(goTime.after(petnesMinRanije) && goTime.before(curTime))
    				{
    					System.out.println("USLO U OVO ZA MJENJANJE!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    					dt.setOccupied(true);
    					dt.setCurrentGuestsOrder(go);
    					dinTabDao.save(dt);
    				}
    			}
    		}
    		
    	}
    	
    }
    
	
}
