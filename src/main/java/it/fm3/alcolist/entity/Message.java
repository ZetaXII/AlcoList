package it.fm3.alcolist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="MESSAGE")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "ID", length = 50, unique = true)
	private long id;
	
	@Column(name = "NOTE", length = 50)
	private String note;
	@OneToOne
	private UserAccount user;
	@Column(name = "CREATION_DATE", length = 50)
	private Date creationDate;
	@OneToOne
	@JsonIgnore
	private Ordination ordination;
	
	
	
	
	public Ordination getOrdination() {
		return ordination;
	}
	public void setOrdination(Ordination ordination) {
		this.ordination = ordination;
	}
	public Message(String note, UserAccount user) {
		super();
		this.note = note;
		this.user = user;
		this.creationDate=new Date();
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public UserAccount getUser() {
		return user;
	}
	public void setUser(UserAccount user) {
		this.user = user;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
	
}
