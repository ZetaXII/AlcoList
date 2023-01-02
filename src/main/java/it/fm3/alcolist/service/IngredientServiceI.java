package it.fm3.alcolist.service;

import it.fm3.alcolist.DTO.IngredientDTO;
import it.fm3.alcolist.entity.Ingredient;

public interface IngredientServiceI {
	Ingredient add(IngredientDTO i) throws Exception;
	Ingredient delete(String uuid) throws Exception;
	Ingredient update(IngredientDTO i) throws Exception;
	Ingredient get(String uuid) throws Exception;
}