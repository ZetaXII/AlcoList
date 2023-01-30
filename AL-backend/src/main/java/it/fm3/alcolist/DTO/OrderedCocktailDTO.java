package it.fm3.alcolist.DTO;

public class OrderedCocktailDTO {
	public String cocktailUuid;
	public String ordinationUuid;
	//public int quantity;
	public OrderedCocktailDTO(String cocktailUuid, String ordinationUuid) {
		super();
		this.cocktailUuid = cocktailUuid;
		this.ordinationUuid = ordinationUuid;
	}

	public OrderedCocktailDTO() {
		super();
	}
}
