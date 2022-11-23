package it.fm3.alcolist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.fm3.alcolist.utils.StatusEnum;

@Entity
@Table(name="ORDINATION")
public class Ordination {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", length = 50, unique = true)
	@JsonIgnore
	private long id;
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Cocktail> cocktails;
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="ID_TABLES", nullable=false)
	private Tables table;
	@Column(name = "TOTAL")
	private Double total;
	@Column(name = "STATUS")
	private StatusEnum status;
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="EXECUTED_BY", nullable=false)
	private UserAccount executed_by;
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="CREATED_BY", nullable=false)	
	private UserAccount created_by;
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="DELIVERED_BY", nullable=false)
	private UserAccount delivered_by;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Cocktail> getCocktails() {
		return cocktails;
	}
	public void setCocktails(List<Cocktail> cocktails) {
		this.cocktails = cocktails;
	}
	public UserAccount getExecuted_by() {
		return executed_by;
	}
	public void setExecuted_by(UserAccount executed_by) {
		this.executed_by = executed_by;
	}
	public UserAccount getCreated_by() {
		return created_by;
	}
	public void setCreated_by(UserAccount created_by) {
		this.created_by = created_by;
	}
	public UserAccount getDelivered_by() {
		return delivered_by;
	}
	public void setDelivered_by(UserAccount delivered_by) {
		this.delivered_by = delivered_by;
	}
	public Tables getTable() {
		return table;
	}
	public void setTable(Tables table) {
		this.table = table;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
}
