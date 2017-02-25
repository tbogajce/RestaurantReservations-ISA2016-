package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Bill;
import netgloo.models.OrderedBeverage;

public interface BillDao extends CrudRepository<Bill, Long>{

}
