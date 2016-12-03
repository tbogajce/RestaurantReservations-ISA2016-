package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "diningTable")
public class DiningTable {
	
	@JsonBackReference("restaurant-diningTable")
	@ManyToOne
	@JoinColumn(name="restaurant_id", referencedColumnName="restaurant_id", nullable=false)
	private Restaurant restaurant_id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long diningTable_id;
	
	@NotNull
	private String diningTable_segment;
	
	@NotNull
	private String diningTable_area;

	
	
	
}
