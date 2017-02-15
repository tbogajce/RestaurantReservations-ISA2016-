package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import netgloo.models.Area;
import netgloo.models.DiningTable;
import netgloo.models.GuestsOrder;

public interface DinningTableDao extends CrudRepository<DiningTable, Long>{
	
	
	//@Query("Select * from DiningTable dt where dt.area = :area")
	//ArrayList<DiningTable> findBySpecificArea(@Param("area")Area area);
	
	//public ArrayList<DiningTable> findAllByArea(Long areaID);
	
	public ArrayList<DiningTable> findAllByArea(Area area);
	public DiningTable findByGeneralTableID(Long generalTableID);

}
