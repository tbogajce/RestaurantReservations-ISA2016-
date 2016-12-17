package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Employee;
import netgloo.models.SystemManager;

public interface EmployeeDao  extends CrudRepository<Employee, Long>{

}
