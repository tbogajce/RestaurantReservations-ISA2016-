package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Bill;
import netgloo.models.HistoryRecord;

public interface HistoryRecordDao extends CrudRepository<HistoryRecord, Long>{

}
