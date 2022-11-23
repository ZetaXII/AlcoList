package it.fm3.alcolist.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="USER_ACCOUNT")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "ID", length = 50, nullable = false, unique = true)
	private long id;
	@Column(name = "NAME", length = 50, nullable = false)
	private String name;
	@Column(name = "SURNAME", length = 50, nullable = false)
	private String surname;
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Role> roles;//TODO DARE DEI NOMI CORRETTI IL CAMPO SUL DB NON E' CHIAMATO CORRETTAMENTE
	@Column(name = "PASSWORD", length = 64, nullable = false)
	private String password;
	@Column(name = "EMAIL", length = 50, nullable = false)
	private String email;
	@Column(name = "DATE_DELETE")
	private Date dateDelete;
	@Column(name = "UUID", length = 50, nullable = false, unique = true)
	private String uuid;
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateDelete() {
		return dateDelete;
	}
	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", name=" + name + ", surname=" + surname + ", roles=" + roles + ", password="
				+ password + ", email=" + email + ", dateDelete=" + dateDelete + ", uuid=" + uuid + "]";
	}

	
	
	

	
	
}
