package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.TableReservation;

public interface TableReservationDao extends CrudRepository<TableReservation, Integer>{

}
