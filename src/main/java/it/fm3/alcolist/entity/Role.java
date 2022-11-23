package it.fm3.alcolist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
//UN UTENTE PUò AVERE PIù RUOLI
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "ID", length = 50, nullable = false, unique = true)
	private long id;
	@Column(name = "NAME", length = 50, nullable = false, unique = true)
	private String name;
	/*
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<UserAccount> users;
	*/
	
	
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	

	
	
}
