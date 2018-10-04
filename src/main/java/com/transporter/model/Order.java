package com.transporter.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//-----------------------------------------------------------------------------
	
	
	private Long id;
	private String origin;
	private String destination;
	private String description;
	private Date plannedDate;
	private Double distance;
	private Float price;
	private Date acceptedDate;

	// @NotNull
	@ManyToOne // (fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	// @NotNull
	@OneToOne // (fetch = FetchType.EAGER)
	@JoinColumn(name = "driver_id")
	private Driver driver;

	public Order() {
	}

	public Order(Long id, String origin, String destination, String description, Date plannedDate,
			Double distance, Float price, Date acceptedDate) {
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.description = description;
		this.plannedDate = plannedDate;
		this.distance = distance;
		this.price = price;
		this.acceptedDate = acceptedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", origin=" + origin + ", destination=" + destination + ", distance="
				+ distance + ", price=" + price + "]";
	}

}
