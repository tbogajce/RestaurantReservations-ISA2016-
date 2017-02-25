package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Bill;
import netgloo.models.WaiterEarnings;

public interface WaiterEarningsDao extends CrudRepository<WaiterEarnings, Long>{

}
