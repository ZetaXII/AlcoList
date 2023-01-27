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
	public Integer page;
	public Integer size;
	@Override
	public String toString() {
		return "CocktailDTO [name=" + name + ", price=" + price + ", description=" + description + ", flavour="
				+ flavour + ", isIBA=" + isIBA + ", inMenu=" + inMenu + ", isAlcoholic=" + isAlcoholic
				+ ", pathFileImg=" + pathFileImg + ", uuid=" + uuid + ", page=" + page + ", size=" + size + "]";
	}
	
	
	
}