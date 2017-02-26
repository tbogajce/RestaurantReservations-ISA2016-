package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "systemManager")
public class SystemManager {

	@Id
	private String systemManagerNickId;

	@NotNull
	private String managerEmail;

	@NotNull
	private String managerName;

	@NotNull
	private String managerLastName;

	@NotNull
	private String managerPassword;

	public SystemManager() {
		super();
	}

	public SystemManager(String system_manager_nick_id, String manager_email, String manager_name,
			String manager_last_name, String manager_password) {
		super();
		this.systemManagerNickId = system_manager_nick_id;
		this.managerEmail = manager_email;
		this.managerName = manager_name;
		this.managerLastName = manager_last_name;
		this.managerPassword = manager_password;
	}

	public SystemManager(String system_manager_nick_id, String manager_password) {
		super();
		this.systemManagerNickId = system_manager_nick_id;
		this.managerPassword = manager_password;
	}

	public SystemManager(String system_manager_nick_id) {
		super();
		this.systemManagerNickId = system_manager_nick_id;
	}

	public String getSystemManagerNickId() {
		return systemManagerNickId;
	}

	public void setSystemManagerNickId(String systemManagerNickId) {
		this.systemManagerNickId = systemManagerNickId;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public String getSystem_manager_nick_id() {
		return systemManagerNickId;
	}

	public void setSystem_manager_nick_id(String system_manager_nick_id) {
		this.systemManagerNickId = system_manager_nick_id;
	}

	public String getManager_email() {
		return managerEmail;
	}

	public void setManager_email(String manager_email) {
		this.managerEmail = manager_email;
	}

	public String getManager_name() {
		return managerName;
	}

	public void setManager_name(String manager_name) {
		this.managerName = manager_name;
	}

	public String getManager_last_name() {
		return managerLastName;
	}

	public void setManager_last_name(String manager_last_name) {
		this.managerLastName = manager_last_name;
	}

	public String getManager_password() {
		return managerPassword;
	}

	public void setManager_password(String manager_password) {
		this.managerPassword = manager_password;
	}

}
