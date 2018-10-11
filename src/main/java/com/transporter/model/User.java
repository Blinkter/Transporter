package com.transporter.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	@NotEmpty
	private String name;

	@Column(name = "last_name")
	@NotEmpty
	private String lastName;

	@Column(name = "active")
	private int active;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User(){
		 
	 }
	
	public User(int active, String email, String name, String lastName, String password, Set<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roles = roles;
	}
}
