package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.TableReservation;
import netgloo.models.User;

public interface TableReservationDao extends CrudRepository<TableReservation, Integer>{
	
	public TableReservation findByTableReservationId(Integer tableReservationId);

}
