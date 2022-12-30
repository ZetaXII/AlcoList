package it.fm3.alcolist.service;

import it.fm3.alcolist.DTO.CocktailDTO;
import it.fm3.alcolist.DTO.CocktailResultDTO;
import it.fm3.alcolist.entity.Cocktail;

public interface CocktailServiceI {
	Cocktail add(CocktailDTO c) throws Exception;
	Cocktail delete(String uuid) throws Exception;
	Cocktail update(CocktailDTO c) throws Exception;
	Cocktail get(String uuid);
	CocktailResultDTO searchByFields(CocktailDTO cocktailDTO) throws Exception;
}