package it.fm3.alcolist.DTO;

public class CocktailDTO {
	public String name;
	public Double price;
	public String description;
	public String flavour;
	public Boolean isIBA;
	public Boolean inMenu;
	public Boolean isAlcoholic;
	public String pathFileImg;
	public String uuid;
	public Integer sold;
	public Integer page;
	public Integer size;
	
	public CocktailDTO() {

	}	
	
	public CocktailDTO(String name, String flavour, Boolean isAlcoholic) {
		this.name = name;
		this.flavour = flavour;
		this.isAlcoholic = isAlcoholic;
	}

	public CocktailDTO(String name, String flavour, Boolean isAlcoholic, Integer page, Integer size) {
		this.name = name;
		this.flavour = flavour;
		this.isAlcoholic = isAlcoholic;
		this.page = page;
		this.size = size;
	}

	public CocktailDTO(String name, Double price, String description, String flavour, Boolean isIBA, Boolean inMenu,
			Boolean isAlcoholic) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.flavour = flavour;
		this.isIBA = isIBA;
		this.inMenu = inMenu;
		this.isAlcoholic = isAlcoholic;
	}
	
	public CocktailDTO(String name, Double price, String description, String flavour, Boolean isIBA, Boolean inMenu,
			Boolean isAlcoholic, Integer sold, String pathFileImg) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.flavour = flavour;
		this.isIBA = isIBA;
		this.inMenu = inMenu;
		this.isAlcoholic = isAlcoholic;
		this.sold = sold;
		this.pathFileImg = pathFileImg;
	}

	@Override
	public String toString() {
		return "CocktailDTO [name=" + name + ", price=" + price + ", description=" + description + ", flavour="
				+ flavour + ", isIBA=" + isIBA + ", inMenu=" + inMenu + ", isAlcoholic=" + isAlcoholic
				+ ", pathFileImg=" + pathFileImg + ", uuid=" + uuid + ", page=" + page + ", size=" + size + "]";
	}
	
	
	
}