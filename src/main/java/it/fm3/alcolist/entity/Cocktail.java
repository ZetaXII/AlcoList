package it.fm3.alcolist.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COCKTAIL")
public class Cocktail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", length = 50, unique = true)
	private long id;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="ID_COCKTAIL", nullable=false)
	private Set<Ingredient>ingredients;
	@Column(name = "NAME", length = 50)
	private String name;
	@Column(name = "PRICE")
	private Double price;
	@Column(name = "DESCRIPTION",length=50)
	private String description;
	@Column(name = "FLAVOUR",length=50)
	private String flavour;//da definire enumeration aspro secco dolce amaro
	@Column(name = "ISIBA")
	private boolean isIBA;//è modificabile?
	@Column(name = "ISALCOHOLIC")
	private boolean isAlcoholic;
	@Column(name = "PATHFILEIMG")
	private String pathFileImg;
	
}
