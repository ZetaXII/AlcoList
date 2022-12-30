package it.fm3.alcolist.DTO;

import java.util.List;

import it.fm3.alcolist.entity.Cocktail;

public class CocktailResultDTO {

	public long totalResult;
	public long startIndex;
	public long itemsPerPage;
	public List<Cocktail> cocktail;

}
