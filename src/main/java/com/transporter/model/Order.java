package com.transporter.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data 
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// -----------------------------------------------------------------------------

	private String origin;
	private String destination;
	private String description;
	private Date plannedDate;
	private Double distance;
	private Float price;
	private Date acceptedDate;

	
	@ManyToOne 
	@JoinColumn(name = "user_id")
	private User user;


	@OneToOne 
	@JoinColumn(name = "driver_id")
	private Driver driver;

	public Order() {
	}

	public Order(Long id, String origin, String destination, String description, Date plannedDate, Double distance,
			Float price, Date acceptedDate) {
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.description = description;
		this.plannedDate = plannedDate;
		this.distance = distance;
		this.price = price;
		this.acceptedDate = acceptedDate;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", origin=" + origin + ", destination=" + destination + ", distance="
				+ distance + ", price=" + price + "]";
	}

}
