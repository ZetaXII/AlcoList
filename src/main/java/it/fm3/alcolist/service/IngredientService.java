package it.fm3.alcolist.service;


import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.IngredientDTO;
import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.entity.Ingredient;
import it.fm3.alcolist.entity.Product;
import it.fm3.alcolist.repository.IngredientRepository;

@Service
@Transactional
public class IngredientService implements IngredientServiceI{
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private ProductServiceI productServiceI;
	@Autowired
	private CocktailServiceI cocktailServiceI;
	
	@Override
	//FIXME controllare che non si stia inserendo lo stesso prodotto altrimenti si duplica la linea
	public Ingredient add(IngredientDTO ingredientDto) throws Exception {
        Ingredient searchIngredient = ingredientRepository.findByCocktailUuidAndProductUuid(ingredientDto.cocktailUuid, ingredientDto.productUuid);
        if(searchIngredient != null) 
            throw new Exception("This Ingredient already exists for this cocktail");
        Ingredient newIngredient= new Ingredient();
        this.buildIngredientByIngredientDTO(newIngredient, ingredientDto);
        //System.out.println("\n\n@@@@@@NUOVO INGREDIENTE: "+newIngredient);
        ingredientRepository.save(newIngredient);
        //System.out.println("\n\n@@@@@@ NUOVO INGREDIENTE: "+newIngredient.toString());
        return newIngredient;
    }

	@Override
	public Ingredient delete(String uuid) throws Exception {
		//TODO completare delete ingredient
		return null;
	}
	
//	@Override
//	public Product delete(String name) throws Exception {
//		Product productToDelete = productServiceI.findByName(name);
//		if(productToDelete==null)
//			throw new Exception("product not found with name: "+name);
//		productToDelte.setDateDelete(new Date());
////		return userToDelete;
//	}

	@Override
	public Ingredient update(IngredientDTO i) throws Exception {
		//TODO testare la funzionalità di modifica quantità e isoptional
		Ingredient ingredientToUpdate = ingredientRepository.findByUuid(i.uuid);
		if(ingredientToUpdate==null)
			throw new Exception("ingredient not found");
		if(i.quantity != null) {
			ingredientToUpdate.setQuantity(i.quantity);
		}
		if(i.isOptional != null) {
			ingredientToUpdate.setOptional(i.isOptional);
		}
//		this.buildIngredientByIngredientDTO(ingredientToUpdate, i);
		return ingredientToUpdate;
	}

	@Override
	public Ingredient get(String uuid) {
		return ingredientRepository.findByUuid(uuid);
	}

	private void buildIngredientByIngredientDTO(Ingredient ingredient, IngredientDTO ingredientDTO) throws Exception {
		Product productToSet = null;
		productToSet = productServiceI.get(ingredientDTO.productUuid);
			if (productToSet==null)
				throw new Exception("product: "+productToSet+" not found");
		ingredient.setProduct(productToSet);
		//System.out.println("setto: "+ingredient.getProduct());
		ingredient.setQuantity(ingredientDTO.quantity);
		ingredient.setOptional(ingredientDTO.isOptional);	
		if(!StringUtils.hasText(ingredientDTO.uuid))
			ingredient.setUuid(UUID.randomUUID().toString());
		else
			ingredient.setUuid(ingredientDTO.uuid);
		Cocktail getC = cocktailServiceI.get(ingredientDTO.cocktailUuid); 
		if(productToSet.getAlcoholicPercentage()>0)
			getC.setAlcoholic(true);
		if(getC == null)
			throw new Exception("Cokctail not found");
		else
			ingredient.setCocktail(getC);	
	}
}
