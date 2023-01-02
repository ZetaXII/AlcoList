package it.fm3.alcolist.service;

import java.util.Set;

import it.fm3.alcolist.DTO.CocktailDTO;
import it.fm3.alcolist.DTO.CocktailResultDTO;
import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.entity.Ingredient;

public interface CocktailServiceI {
	Cocktail add(CocktailDTO c) throws Exception;
	Cocktail delete(String uuid) throws Exception;
	Cocktail update(CocktailDTO c) throws Exception;
	Cocktail get(String uuid) throws Exception;
	CocktailResultDTO searchByFields(CocktailDTO cocktailDTO) throws Exception;
	Set<Ingredient> getIngredients(String uuid);
}