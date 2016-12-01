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

@Entity
@Table(name = "waiterIncome")
public class WaiterIncome {
	
	@ManyToOne
	@JoinColumn(name = "employee_id", table = "employee")
	private Long employee_id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long waiterIncome_id;
	
	@NotNull
	private Float waiterIncome_daily;
	
	@NotNull
	private Date waiterIncome_date;

	public WaiterIncome(Long employee_id, Long waiterIncome_id, Float waiterIncome_daily, Date waiterIncome_date) {
		super();
		this.employee_id = employee_id;
		this.waiterIncome_id = waiterIncome_id;
		this.waiterIncome_daily = waiterIncome_daily;
		this.waiterIncome_date = waiterIncome_date;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Long getWaiterIncome_id() {
		return waiterIncome_id;
	}

	public void setWaiterIncome_id(Long waiterIncome_id) {
		this.waiterIncome_id = waiterIncome_id;
	}

	public Float getWaiterIncome_daily() {
		return waiterIncome_daily;
	}

	public void setWaiterIncome_daily(Float waiterIncome_daily) {
		this.waiterIncome_daily = waiterIncome_daily;
	}

	public Date getWaiterIncome_date() {
		return waiterIncome_date;
	}

	public void setWaiterIncome_date(Date waiterIncome_date) {
		this.waiterIncome_date = waiterIncome_date;
	}
	
	
	
}
