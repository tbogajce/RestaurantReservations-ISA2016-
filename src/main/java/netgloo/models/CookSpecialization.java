package netgloo.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cookSpecialization")
public class CookSpecialization {

	@JsonBackReference("employee-cookSpecialization")
	@ManyToOne
	@JoinColumn(name="employee", referencedColumnName="employeeId", nullable=false)
	private Employee employee;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cookSpecializationId;
	
	
	@NotNull
	private String specialization;


	public CookSpecialization(Employee employee, Long cookSpecializationId, String specialization) {
		super();
		this.employee = employee;
		this.cookSpecializationId = cookSpecializationId;
		this.specialization = specialization;
	}
	
	


	public CookSpecialization() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Long getCookSpecializationId() {
		return cookSpecializationId;
	}


	public void setCookSpecializationId(Long cookSpecializationId) {
		this.cookSpecializationId = cookSpecializationId;
	}


	public String getSpecialization() {
		return specialization;
	}


	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	
	
}
