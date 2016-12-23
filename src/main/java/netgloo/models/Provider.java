package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "provider")
public class Provider {

	@Id
	private String providerNickId;

	@NotNull
	private String providerMail;

	@NotNull
	private String providerName;

	@NotNull
	private String providerSurname;

	@NotNull
	private String providerPassword;

	public Provider() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Provider(String providerNickId, String providerMail, String providerName, String providerSurname,
			String providerPassword) {
		super();
		this.providerNickId = providerNickId;
		this.providerMail = providerMail;
		this.providerName = providerName;
		this.providerSurname = providerSurname;
		this.providerPassword = providerPassword;
	}

	public String getProviderNickId() {
		return providerNickId;
	}

	public void setProviderNickId(String providerNickId) {
		this.providerNickId = providerNickId;
	}

	public String getProviderMail() {
		return providerMail;
	}

	public void setProviderMail(String providerMail) {
		this.providerMail = providerMail;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderSurname() {
		return providerSurname;
	}

	public void setProviderSurname(String providerSurname) {
		this.providerSurname = providerSurname;
	}

	public String getProviderPassword() {
		return providerPassword;
	}

	public void setProviderPassword(String providerPassword) {
		this.providerPassword = providerPassword;
	}

}
