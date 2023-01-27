package it.fm3.alcolist.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="COCKTAIL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cocktail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "ID", length = 50, unique = true)
	private long id;
	
	
	@OneToMany(mappedBy="cocktail",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private Set<Ingredient>ingredients = new HashSet<Ingredient>();
	
	@Column(name = "NAME", length = 50)
	private String name;
	@Column(name = "PRICE")
	private Double price;
	@Column(name = "DESCRIPTION",length=500)
	private String description;
	@Column(name = "FLAVOUR",length=50)
	private String flavour;//UPGRADE attualmente sul db viene salvata una stringa
						   //bosognerebber gestire con enum flavour
	@Column(name = "ISIBA")//C'è ma bisogna integrare le query
	private boolean isIBA;
	@Column(name = "ISALCOHOLIC")
	private boolean isAlcoholic;
	@Column(name = "PATHFILEIMG")
	private String pathFileImg;
	@Column(name = "UUID", length = 50, nullable = false, unique = true)
	private String uuid;
	@Column(name = "INMENU")
	private boolean inMenu;
	@Column(name = "SOLD")
	private Integer sold = 0;
	/*
	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JsonIgnore
	private List<Ordination> ordinations;
	*/
	
	public Cocktail() {
	}

	public Cocktail(String name, Double price, String description, String flavour, boolean isIBA, boolean isAlcoholic,
			boolean inMenu, Integer sold) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.flavour = flavour;
		this.isIBA = isIBA;
		this.isAlcoholic = isAlcoholic;
		this.inMenu = inMenu;
		this.sold = sold;
		this.uuid = UUID.randomUUID().toString();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFlavour() {
		return flavour;
	}
	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}
	public boolean isIBA() {
		return isIBA;
	}
	public void setIBA(boolean isIBA) {
		this.isIBA = isIBA;
	}
	public boolean isAlcoholic() {
		return isAlcoholic;
	}
	public void setAlcoholic(boolean isAlcoholic) {
		this.isAlcoholic = isAlcoholic;
	}
	public String getPathFileImg() {
		return pathFileImg;
	}
	public void setPathFileImg(String pathFileImg) {
		this.pathFileImg = pathFileImg;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/*
	public List<Ordination> getOrdinations() {
		return ordinations;
	}
	public void setOrdinations(List<Ordination> ordinations) {
		this.ordinations = ordinations;
	}*/
	public boolean isInMenu() {
		return inMenu;
	}
	public void setInMenu(boolean inMenu) {
		this.inMenu = inMenu;
	}
	
	public Integer getSold() {
		return sold;
	}
	public void setSold(Integer sold) {
		this.sold = sold;
	}
	@Override
	public String toString() {
		return "Cocktail [id=" + id + ", ingredients=" + ingredients + ", name=" + name + ", price=" + price
				+ ", description=" + description + ", flavour=" + flavour + ", isIBA=" + isIBA + ", isAlcoholic="
				+ isAlcoholic + ", pathFileImg=" + pathFileImg + ", uuid=" + uuid + ", inMenu=" + inMenu + ", sold="
				+ sold + "]";
	}
}
