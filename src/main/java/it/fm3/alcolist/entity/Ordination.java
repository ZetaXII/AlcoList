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
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "ordinations")
	private List<Cocktail> orderedCocktails;
	
	@Column(name = "UUID", length = 50, nullable = false, unique = true)
	private String uuid;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="ID_TABLES", nullable=false)
	private Tables table;
	@Column(name = "TOTAL")
	private Double total;
	@Column(name = "STATUS")
	private StatusEnum status;
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="executedBy")
	private UserAccount executedBy;
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="createdBy", nullable=false)	
	private UserAccount createdBy;
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="deliveredBy")
	private UserAccount deliveredBy;
	@Column(name = "DATE_LAST_MODIFIED")
	private Date dateLastModified;
	@Column(name = "DATE_CREATION")
	private Date dateCreation;
	
	
	public Date getDateLastModified() {
		return dateLastModified;
	}
	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserAccount getExecutedBy() {
		return executedBy;
	}
	public void setExecutedBy(UserAccount executedBy) {
		this.executedBy = executedBy;
	}
	public UserAccount getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserAccount createdBy) {
		this.createdBy = createdBy;
	}
	public UserAccount getDeliveredBy() {
		return deliveredBy;
	}
	public void setDeliveredBy(UserAccount deliveredBy) {
		this.deliveredBy = deliveredBy;
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
	public List<Cocktail> getOrderedCocktails() {
		return orderedCocktails;
	}
	public void setOrderedCocktails(List<Cocktail> orderedCocktails) {
		this.orderedCocktails = orderedCocktails;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
