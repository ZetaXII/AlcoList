package it.fm3.alcolist.service;


import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.CocktailDTO;
import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.repository.CocktailRepository;

@Service
@Transactional
public class CocktailService implements CocktailServiceI{
	@Autowired
	CocktailRepository cocktailRepository;
	
	
	@Override
	public Cocktail add(CocktailDTO cocktailDto) throws Exception {
		//QQQ è giusto avere user account se non ho un istanza di userAccount sul db? (Vale anche per il prodotto)
		Cocktail newCocktail= new Cocktail();
		this.buildCocktailByCocktailDTO(newCocktail, cocktailDto);
		if(cocktailRepository.findByUuid(cocktailDto.uuid) != null)
			throw new Exception("Cocktail already exists");
		cocktailRepository.save(newCocktail);
		 System.out.println("\n\n@@@@@@ NUOVO PRODOTTO: "+newCocktail);
		 return newCocktail;
	}

	@Override
	public Cocktail delete(String uuid) throws Exception {
		//TODO completare delete ingredient
		return null;
	}
	
//	@Override
//	public Product delete(String name) throws Exception {
//		Product productToDelete = productRepository.findByName(name);
//		if(productToDelete==null)
//			throw new Exception("product not found with name: "+name);
//		productToDelte.setDateDelete(new Date());
////		return userToDelete;
//	}

	@Override
	public Cocktail update(CocktailDTO c) throws Exception {
		Cocktail cocktailToUpdate = cocktailRepository.findByUuid(c.uuid);
		if(cocktailToUpdate==null)
			throw new Exception("cocktail not found");
		this.buildCocktailByCocktailDTO(cocktailToUpdate, c);
		return cocktailToUpdate;
	}

	@Override
	public Cocktail get(String uuid) {
		return cocktailRepository.findByUuid(uuid);
	}
	
	private void buildCocktailByCocktailDTO(Cocktail cocktail, CocktailDTO cocktailDTO) throws Exception {
		cocktail.setName(cocktailDTO.name);
		cocktail.setPrice(cocktailDTO.price);
		cocktail.setDescription(cocktailDTO.description);
		cocktail.setFlavour(cocktailDTO.flavour);
		cocktail.setIBA(cocktailDTO.isIBA);
		cocktail.setAlcoholic(cocktailDTO.isAlcoholic);
		cocktail.setPathFileImg(cocktailDTO.pathFileImg);	
		if(!StringUtils.hasText(cocktailDTO.uuid))
			cocktail.setUuid(UUID.randomUUID().toString());
		else
			cocktail.setUuid(cocktailDTO.uuid);
	}
	
}
