package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order")
public class Order {

	@ManyToOne
	@JoinColumn(name = "restaurant_id", table = "restaurant")
	private Long restaurant_id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", table = "employee")
	private Long employee_id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long order_id;

	@NotNull
	private Float order_price;

	@NotNull
	private String order_status;

	public Order(Long restaurant_id, Long employee_id, Long order_id, Float order_price, String order_status) {
		super();
		this.restaurant_id = restaurant_id;
		this.employee_id = employee_id;
		this.order_id = order_id;
		this.order_price = order_price;
		this.order_status = order_status;
	}

	public Long getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Float getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Float order_price) {
		this.order_price = order_price;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	
	
}
