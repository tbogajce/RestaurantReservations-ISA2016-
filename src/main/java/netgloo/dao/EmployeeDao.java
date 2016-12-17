package netgloo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Employee;
import netgloo.models.Friendships;
import netgloo.models.SystemManager;
import netgloo.models.User;

public interface EmployeeDao  extends CrudRepository<Employee, Long>{

	//public List<Friendships> findByLoveGiver(User love_giver);
	
	//public Employee findByUser_Id(Integer user_id);
	
}
