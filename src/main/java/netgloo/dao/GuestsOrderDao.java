package netgloo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import netgloo.models.DiningTable;
import netgloo.models.GuestsOrder;
import netgloo.models.OrderedBeverage;
import netgloo.models.OrderedMeal;
import netgloo.models.Restaurant;

public interface GuestsOrderDao extends CrudRepository<GuestsOrder, Integer>{

	//select avg(t.price) from Ticket t join t.flight f where f.number = :flightNumber
	//@Query("FROM orderedBeverage WHERE a.eventDateTime < :before ORDER BY a.eventDateTime DESC")
	@Query("Select ob FROM OrderedBeverage ob join ob.guestsOrder go where go.restaurant = :restaurantX and go.orderReceivedTime > :sTimeX and go.orderReceivedTime < :eTimeX ORDER BY ob.orderedBeverageID DESC")
    ArrayList<OrderedBeverage> findByRestaurantAndDates(@Param("restaurantX") Restaurant restaurant,@Param("sTimeX") Timestamp sTime,@Param("eTimeX") Timestamp eTime, Pageable pageable);
	
	
	//@Query("Select om FROM OrderedMeal om join om.guestsOrder go where om in (select omm from OrderedMeal omm where go.restaurant =: ) ")
	//@Query("Select om from OrderedMeal om where om.guestsOrder in (select go from OrderedMeal omm join omm.guestsOrder go where go.restaurant = :restaurantX and go.orderReceivedTime < :eTimeX ORDER BY go.orderReceivedTime DESC")
	@Query("Select om FROM OrderedMeal om join om.guestsOrder go where go.restaurant = :restaurantX and go.orderReceivedTime > :sTimeX and go.orderReceivedTime < :eTimeX ORDER BY om.orderedMealID DESC")
    ArrayList<OrderedMeal> findByRestaurantAndDatesMeal(@Param("restaurantX") Restaurant restaurant,@Param("sTimeX") Timestamp sTime,@Param("eTimeX") Timestamp eTime, Pageable pageable);
	/*
	select order_date, order_amount
	from customers
	join orders
	   on customers.customer_id = orders.customer_id
	where customer_id = 3
	*/
	
	//@Query("Select go from GuestsOrder go where diningTable = :dt")
	//public GuestsOrder findOneByDiningTable(@Param("dt") DiningTable dt);
	//@Query("Select goo from GuestsOrder goo where goo.orderID = (select goo2.orderID from GuestsOrder goo2 where goo2.diningTable = :dtab order by goo2.orderReceivedTime desc limit 1)")
	public ArrayList<GuestsOrder> findAllByDiningTable(DiningTable diningTable);
	
	//@Query("Select om FROM OrderedMeal om join om.guestsOrder go where go.restaurant = :restaurantX and go.orderReceivedTime > :sTimeX and go.orderReceivedTime < :eTimeX ORDER BY om.orderedMealID DESC")
    @Query("SELECT go from GuestsOrder go where orderReceivedTime > :sTimeX and orderReceivedTime < :eTimeX ORDER BY orderID")
	ArrayList<GuestsOrder> getGuestsOrderesForToday(@Param("sTimeX") Timestamp sTime,@Param("eTimeX") Timestamp eTime);
    
    
    @Query("SELECT go from GuestsOrder go where orderReceivedTime > :sTimeX and orderReceivedTime < :eTimeX and diningTable = :tab ORDER BY orderID")
   	ArrayList<GuestsOrder> getGuestsOrderesForTimeIntervalAndSpecificTable(@Param("sTimeX") Timestamp sTime,@Param("eTimeX") Timestamp eTime, @Param("tab") DiningTable tab);
	
	
	//public GuestsOrder findBydiningTable(DiningTable dt);
	
}
