package it.fm3.alcolist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", length = 50, unique = true)
	private long id;
	@Column(name = "NAME", length = 50, unique = true)
	private String name;
	@Column(name = "SURNAME", length = 50, unique = true)
	private String surname;
	@Column(name = "ROLE", length = 50, unique = true)
	private Role role;
}
