package it.fm3.alcolist.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="COCKTAIL")
public class Cocktail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
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
	private String flavour;//TODO da definire enumeration aspro secco dolce amaro
	@Column(name = "ISIBA")
	private boolean isIBA;
	@Column(name = "ISALCOHOLIC")
	private boolean isAlcoholic;
	@Column(name = "PATHFILEIMG")
	private String pathFileImg;
	@Column(name = "UUID", length = 50, nullable = false, unique = true)
	private String uuid;
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Ordination> ordinations;
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
	public List<Ordination> getOrdinations() {
		return ordinations;
	}
	public void setOrdinations(List<Ordination> ordinations) {
		this.ordinations = ordinations;
	}
	@Override
	public String toString() {
		return "Cocktail [id=" + id + ", ingredients=" + ingredients + ", name=" + name + ", price=" + price
				+ ", description=" + description + ", flavour=" + flavour + ", isIBA=" + isIBA + ", isAlcoholic="
				+ isAlcoholic + ", pathFileImg=" + pathFileImg + ", uuid=" + uuid + ", ordinations=" + ordinations
				+ "]";
	}	
}
