package com.transporter.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data 
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	// -----------------------------------------------------------------------------
	@NotEmpty
	@Column(name = "origin")
	private String origin;
	// -----------------------------------------------------------------------------
	@NotEmpty
	@Column(name = "destination")
	private String destination;
	// -----------------------------------------------------------------------------
	@Column(name = "description")	
	private String description;
	// -----------------------------------------------------------------------------
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "planned_date")	
	private LocalDate plannedDate;
	// -----------------------------------------------------------------------------
	private Double distance;
	// -----------------------------------------------------------------------------
	private Float price;
	// -----------------------------------------------------------------------------	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Order() {
	}

	public Order(Long id, String origin, String destination, String description, Date plannedDate, Double distance,
			Float price, Date acceptedDate, User user) {
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.description = description;
		this.distance = distance;
		this.price = price;
		this.user = user;
	}
}
