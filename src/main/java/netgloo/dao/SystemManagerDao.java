package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.SystemManager;
import javax.transaction.Transactional;

@Transactional
public interface SystemManagerDao  extends CrudRepository<SystemManager, String>{

	//public SystemManager findBySystem_manager_nick_id(String system_manager_nick_id);
	
	
	
}
