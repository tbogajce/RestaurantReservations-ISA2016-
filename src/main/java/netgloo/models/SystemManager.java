package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name="systemManager")
public class SystemManager {
	
	
	@Id
	private String system_manager_nick_id;
	
	@NotNull
	private String manager_email;
	
	@NotNull
	private String manager_name;
	
	@NotNull
	private String manager_last_name;
	
	@NotNull 
	private String manager_password;
	
	public SystemManager()
	{
		super();
	}

	public SystemManager(String system_manager_nick_id, String manager_email, String manager_name,
			String manager_last_name, String manager_password) {
		super();
		this.system_manager_nick_id = system_manager_nick_id;
		this.manager_email = manager_email;
		this.manager_name = manager_name;
		this.manager_last_name = manager_last_name;
		this.manager_password = manager_password;
	}
	
	
	public SystemManager(String system_manager_nick_id, String manager_password)
	{
		super();
		this.system_manager_nick_id = system_manager_nick_id;
		this.manager_password = manager_password;
	}
	
	public SystemManager(String system_manager_nick_id)
	{
		super();
		this.system_manager_nick_id=system_manager_nick_id;
	}
	
	

	public String getSystem_manager_nick_id() {
		return system_manager_nick_id;
	}

	public void setSystem_manager_nick_id(String system_manager_nick_id) {
		this.system_manager_nick_id = system_manager_nick_id;
	}

	public String getManager_email() {
		return manager_email;
	}

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	public String getManager_last_name() {
		return manager_last_name;
	}

	public void setManager_last_name(String manager_last_name) {
		this.manager_last_name = manager_last_name;
	}

	public String getManager_password() {
		return manager_password;
	}

	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}
	
	
	
	

}
