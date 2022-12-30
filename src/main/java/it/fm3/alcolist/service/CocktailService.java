package it.fm3.alcolist.service;


import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.CocktailDTO;
import it.fm3.alcolist.DTO.CocktailResultDTO;
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
		cocktail.setInMenu(cocktailDTO.inMenu);
		cocktail.setAlcoholic(cocktailDTO.isAlcoholic);
		cocktail.setPathFileImg(cocktailDTO.pathFileImg);	
		if(!StringUtils.hasText(cocktailDTO.uuid))
			cocktail.setUuid(UUID.randomUUID().toString());
		else
			cocktail.setUuid(cocktailDTO.uuid);
	}
	
	@Override
	public CocktailResultDTO searchByFields(CocktailDTO cocktailDTO) throws Exception {
		CocktailResultDTO cocktailResult = new CocktailResultDTO();
		
		cocktailResult.cocktail = searchByFieldsRes(cocktailDTO , cocktailResult);
		
		if(cocktailResult.cocktail.size() == 0 ) {
			cocktailResult.totalResult=0;
			cocktailResult.itemsPerPage =0;
			cocktailResult.startIndex=0;
		}else {
			cocktailResult.itemsPerPage = cocktailResult.cocktail.size();
		}
		return cocktailResult;
	}
	
	private List<Cocktail>  searchByFieldsRes(CocktailDTO cocktailDTO,CocktailResultDTO cocktailResultDTO) throws Exception  {
		Pageable pageable = null;
		if(cocktailDTO.page != null && cocktailDTO.size != null) {
			pageable = PageRequest.of(cocktailDTO.page.intValue(), cocktailDTO.size.intValue());
			if(cocktailDTO.page.intValue() > 0)
				cocktailResultDTO.startIndex = cocktailDTO.page.intValue() * cocktailDTO.size.intValue() + 1;
			else
				cocktailResultDTO.startIndex = cocktailDTO.page.intValue() + 1;
		}
		return searchByFieldsSimple(cocktailDTO, pageable,cocktailResultDTO);
	}
	
	private List<Cocktail> searchByFieldsSimple(CocktailDTO cocktailDTO, Pageable pageable,CocktailResultDTO cocktailResultDTO) throws Exception {
		if(!StringUtils.hasText(cocktailDTO.name) && !StringUtils.hasText(cocktailDTO.flavour) && (cocktailDTO.isAlcoholic)==null) {
			//SE TUTTI VUOTI RICERCA TUTTO
			cocktailResultDTO.totalResult= cocktailRepository.count();
			if(pageable!=null)
				return cocktailRepository.findAll(pageable).getContent();
			else return cocktailRepository.findAll();
		}
		else if(StringUtils.hasText(cocktailDTO.name) && !StringUtils.hasText(cocktailDTO.flavour) && (cocktailDTO.isAlcoholic)==null) {
			//SOLO NOME
			cocktailResultDTO.totalResult = cocktailRepository.countByNameContainingIgnoreCase(cocktailDTO.name);
			if(pageable!=null)
				return cocktailRepository.findByName(pageable, cocktailDTO.name);
			else return cocktailRepository.findByName(cocktailDTO.name);
		}	
		else if(!StringUtils.hasText(cocktailDTO.name) && StringUtils.hasText(cocktailDTO.flavour) && (cocktailDTO.isAlcoholic)==null) {
			//SOLO GUSTO
			cocktailResultDTO.totalResult = cocktailRepository.countByFlavourContainingIgnoreCase(cocktailDTO.flavour);
			if(pageable!=null)
				return cocktailRepository.findByFlavour(pageable, cocktailDTO.flavour);
			else return cocktailRepository.findByFlavour(cocktailDTO.flavour);
		}
		else if(!StringUtils.hasText(cocktailDTO.name) && !StringUtils.hasText(cocktailDTO.flavour) && (cocktailDTO.isAlcoholic)!=null) {
			//SOLO ALCOLICO
			cocktailResultDTO.totalResult = cocktailRepository.countByIsAlcoholic(cocktailDTO.isAlcoholic);
			if(pageable!=null)
				return cocktailRepository.findByIsAlcoholic(pageable, cocktailDTO.isAlcoholic);
			else return cocktailRepository.findByIsAlcoholic(cocktailDTO.isAlcoholic);
		}
		else if(StringUtils.hasText(cocktailDTO.name) && StringUtils.hasText(cocktailDTO.flavour) && (cocktailDTO.isAlcoholic)==null) {
			// NOME-GUSTO
			cocktailResultDTO.totalResult = cocktailRepository.countByNameAndFlavourContainingIgnoreCase(cocktailDTO.name, cocktailDTO.flavour);
			if(pageable!=null)
				return cocktailRepository.findByNameAndFlavour(pageable, cocktailDTO.name, cocktailDTO.flavour);
			else return cocktailRepository.findByNameAndFlavour(cocktailDTO.name, cocktailDTO.flavour);
		}
		else if(StringUtils.hasText(cocktailDTO.name) && !StringUtils.hasText(cocktailDTO.flavour) && (cocktailDTO.isAlcoholic)!=null) {
			// NOME-ALCOLICO
			cocktailResultDTO.totalResult = cocktailRepository.countByNameAndIsAlcoholic(cocktailDTO.name, cocktailDTO.isAlcoholic);
			if(pageable!=null)
				return cocktailRepository.findByNameAndIsAlcoholic(pageable, cocktailDTO.name, cocktailDTO.isAlcoholic);
			else return cocktailRepository.findByNameAndIsAlcoholic(cocktailDTO.name, cocktailDTO.isAlcoholic);
		}
		else if(!StringUtils.hasText(cocktailDTO.name) && StringUtils.hasText(cocktailDTO.flavour) && (cocktailDTO.isAlcoholic)!=null) {
			// GUSTO-ALCOLICO
			cocktailResultDTO.totalResult = cocktailRepository.countByFlavourAndIsAlcoholic(cocktailDTO.flavour, cocktailDTO.isAlcoholic);
			if(pageable!=null)
				return cocktailRepository.findByFlavourAndIsAlcoholic(pageable, cocktailDTO.flavour, cocktailDTO.isAlcoholic);
			else return cocktailRepository.findByFlavourAndIsAlcoholic(cocktailDTO.flavour, cocktailDTO.isAlcoholic);
		}
		else {
			//COMPLETA
			cocktailResultDTO.totalResult = cocktailRepository.countByNameAndFlavourAndIsAlcoholic(cocktailDTO.name, cocktailDTO.flavour, cocktailDTO.isAlcoholic);
			if(pageable!=null)
				return cocktailRepository.findByNameAndFlavourAndIsAlcoholic(pageable, cocktailDTO.name, cocktailDTO.flavour, cocktailDTO.isAlcoholic);
			else return cocktailRepository.findByNameAndFlavourAndIsAlcoholic(cocktailDTO.name, cocktailDTO.flavour, cocktailDTO.isAlcoholic);
		}
	
		//cocktailResultDTO.totalResult= cocktailRepository.count();
		//if(pageable!=null)
			//return cocktailRepository.findAll(pageable).getContent();
		//else return cocktailRepository.findAll();
	}
}
