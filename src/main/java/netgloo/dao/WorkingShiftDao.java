package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Restaurant;
import netgloo.models.User;
import netgloo.models.WorkingShift;

public interface WorkingShiftDao extends CrudRepository<WorkingShift, Long>{
	
	/*
	public List<Customer> listCustomers() {
	    Session session = this.sessionFactory.getCurrentSession();
	    List<Customer> customersList = session.createQuery("from Customer c LEFT JOIN Person p ON p.idPerson = c.idCustomer").list();

	    for (Customer c : customersList) {
	        logger.info("Customer List::" + c);
	    }

	    return customersList;
	}
	*/
	
	
	public ArrayList<WorkingShift> findByRestaurant(Restaurant restaurant);
}
