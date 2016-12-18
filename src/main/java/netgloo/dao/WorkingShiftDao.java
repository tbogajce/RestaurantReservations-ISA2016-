package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.User;
import netgloo.models.WorkingShift;

public interface WorkingShiftDao extends CrudRepository<WorkingShift, Long>{

}
