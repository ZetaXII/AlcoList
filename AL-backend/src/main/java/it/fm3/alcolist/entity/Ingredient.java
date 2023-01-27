package it.fm3.alcolist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", length = 50, unique = true)
	@JsonIgnore
	private long id;
	
	
	@OneToOne
	@JoinColumn(name="PRODUCT",nullable = false)
	private Product product;
	
	private Integer quantity;//ml
	private boolean isOptional;
	private boolean isPresent;
	@Column(name = "UUID", length = 50, nullable = false, unique = true)
	private String uuid;
	
	
	@OneToOne
	@JoinColumn(name="COCKTAIL", nullable = false)
	@JsonIgnore
	private Cocktail cocktail;
	
	public boolean isOptional() {
		return isOptional;
	}
	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Cocktail getCocktail() {
		return cocktail;
	}
	public void setCocktail(Cocktail c) {
		this.cocktail = c;
	}
	
	
	public boolean isPresent() {
		return isPresent;
	}
	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", product=" + product + ", quantity=" + quantity + ", isOptional=" + isOptional
				+ ", isPresent=" + isPresent + ", uuid=" + uuid + ", cocktail=" + cocktail + "]";
	}
}
