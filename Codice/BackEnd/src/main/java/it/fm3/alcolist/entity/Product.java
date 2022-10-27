package it.fm3.alcolist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", length = 50, unique = true)
	private long id;
	@Column(name = "NAME", length = 50)
	private String name;
	@Column(name = "CATEGORY", length = 50)
	private String category;
	@Column(name = "ALCOHOLIC_PERCENTAGE")
	private Double alcoholicPercentage;
	@Column(name = "IS_PRESENT")
	private boolean isPresent;
	@Column(name = "ML")
	private Integer ml;
	@Column(name = "PATHFILEIMG")
	private String pathFileImg;
	
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getAlcoholicPercentage() {
		return alcoholicPercentage;
	}
	public void setAlcoholicPercentage(Double alcoholicPercentage) {
		this.alcoholicPercentage = alcoholicPercentage;
	}
	public boolean isPresent() {
		return isPresent;
	}
	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	public Integer getMl() {
		return ml;
	}
	public void setMl(Integer ml) {
		this.ml = ml;
	}
	
	
}