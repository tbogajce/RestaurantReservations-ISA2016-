package netgloo.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Bill;
import netgloo.models.HistoryRecord;
import netgloo.models.User;

public interface HistoryRecordDao extends CrudRepository<HistoryRecord, Long>{
	
	
	public ArrayList<HistoryRecord> findAllByUser(User user);

}
