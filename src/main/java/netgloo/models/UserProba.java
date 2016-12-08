package netgloo.models;

public class UserProba {
	private String email;
	private String password;
	private String name;
	private String surname;
	private String birthDate;
	
	
	public UserProba(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public UserProba(String email, String password, String name, String surname, String birthDate) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}
	public UserProba() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
}
