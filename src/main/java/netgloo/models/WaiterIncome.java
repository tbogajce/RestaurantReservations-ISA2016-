package netgloo.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;



/* NE KORISTITI OVUUUUU */

@Entity
@Table(name = "waiterIncome")
public class WaiterIncome {
	
	@JsonBackReference("employee-waiterIncome")
	@ManyToOne
	@JoinColumn(name="employeeId", referencedColumnName="employeeId", nullable=false)
	private Employee employeeId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long waiterIncomeId;
	
	@NotNull
	private Float waiterIncomeDaily;
	
	@NotNull
	private Date waiterIncomeDate;

	public WaiterIncome(Employee employee_id, Long waiterIncome_id, Float waiterIncome_daily, Date waiterIncome_date) {
		super();
		this.employeeId = employee_id;
		this.waiterIncomeId = waiterIncome_id;
		this.waiterIncomeDaily = waiterIncome_daily;
		this.waiterIncomeDate = waiterIncome_date;
	}

	public WaiterIncome() {
		super();
	}
	
	

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public Long getWaiterIncomeId() {
		return waiterIncomeId;
	}

	public void setWaiterIncomeId(Long waiterIncomeId) {
		this.waiterIncomeId = waiterIncomeId;
	}

	public Float getWaiterIncomeDaily() {
		return waiterIncomeDaily;
	}

	public void setWaiterIncomeDaily(Float waiterIncomeDaily) {
		this.waiterIncomeDaily = waiterIncomeDaily;
	}

	public Date getWaiterIncomeDate() {
		return waiterIncomeDate;
	}

	public void setWaiterIncomeDate(Date waiterIncomeDate) {
		this.waiterIncomeDate = waiterIncomeDate;
	}

	public Employee getEmployee_id() {
		return employeeId;
	}

	public void setEmployee_id(Employee employee_id) {
		this.employeeId = employee_id;
	}

	public Long getWaiterIncome_id() {
		return waiterIncomeId;
	}

	public void setWaiterIncome_id(Long waiterIncome_id) {
		this.waiterIncomeId = waiterIncome_id;
	}

	public Float getWaiterIncome_daily() {
		return waiterIncomeDaily;
	}

	public void setWaiterIncome_daily(Float waiterIncome_daily) {
		this.waiterIncomeDaily = waiterIncome_daily;
	}

	public Date getWaiterIncome_date() {
		return waiterIncomeDate;
	}

	public void setWaiterIncome_date(Date waiterIncome_date) {
		this.waiterIncomeDate = waiterIncome_date;
	}

	
	
	
}
