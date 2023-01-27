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
@Table(name = "ORDERED_COCKTAIL")
public class OrderedCocktail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", length = 50, unique = true)
	@JsonIgnore
	private long id;
	
	@Column(name = "QUANTITY")
	private Integer quantity; //numbersOfCocktails
	
	@OneToOne
	@JoinColumn(name="COCKTAIL", nullable = false)
	private Cocktail cocktail;
	
	@OneToOne
	@JoinColumn(name="ORDINATION", nullable = false)
	@JsonIgnore
	private Ordination ordination;
	
	
	
	public OrderedCocktail() {

	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Cocktail getCocktail() {
		return cocktail;
	}
	public void setCocktail(Cocktail c) {
		this.cocktail = c;
	}
	public Ordination getOrdination() {
		return ordination;
	}
	public void setOrdination(Ordination ordination) {
		this.ordination = ordination;
	}
	@Override
	public String toString() {
		return "OrderedCocktail [id=" + id + ", quantity=" + quantity + "]";
	}
	
	
	
		
}
