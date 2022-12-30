package it.fm3.alcolist.service;


import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.ProductDTO;
import it.fm3.alcolist.DTO.ProductResultDTO;
import it.fm3.alcolist.entity.Product;
import it.fm3.alcolist.repository.ProductRepository;

@Service
@Transactional
public class ProductService implements ProductServiceI{
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product add(ProductDTO productDto) throws Exception {
		//QQQ è giusto avere user account se non ho un istanza di userAccount sul db? (Vale anche per il prodotto)
		Product newProduct= new Product();
		this.buildProductByProductDTO(newProduct,productDto);
		if(productRepository.findByUuid(productDto.uuid) != null)
			throw new Exception("product already exists");
		productRepository.save(newProduct);
		 System.out.println("\n\n@@@@@@ NUOVO PRODOTTO: "+newProduct);
		 return newProduct;
	}

	@Override
	public Product delete(String uuid) throws Exception {
		//TODO completare delete product
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
	public Product update(ProductDTO p) throws Exception {
		Product productsToUpdate = productRepository.findByUuid(p.uuid);
		if(productsToUpdate==null)
			throw new Exception("product not found");
		this.buildProductByProductDTO(productsToUpdate,p);
		return productsToUpdate;
	}

	@Override
	public Product get(String uuid) {
		return productRepository.findByUuid(uuid);
	}
	
	private void buildProductByProductDTO(Product product, ProductDTO productDTO) {
		product.setName(productDTO.name);
		product.setCategory(productDTO.category);
		product.setAlcoholicPercentage(productDTO.alcholicPercentage);
		product.setPresent(productDTO.present);
		product.setMl(productDTO.ml);
		product.setPathFileImg(productDTO.pathFileImg);
		if(!StringUtils.hasText(productDTO.uuid))
			product.setUuid(UUID.randomUUID().toString());
		else
			product.setUuid(productDTO.uuid);
	}
	
	@Override
	public ProductResultDTO searchByFields(ProductDTO productDTO) throws Exception {
		ProductResultDTO productResult = new ProductResultDTO();
		
		productResult.product = searchByFieldsRes(productDTO , productResult);
		
		if(productResult.product.size() == 0 ) {
			productResult.totalResult=0;
			productResult.itemsPerPage =0;
			productResult.startIndex=0;
		}else {
			productResult.itemsPerPage = productResult.product.size();
		}
		return productResult;
	}
	
	private List<Product>  searchByFieldsRes(ProductDTO productDTO,ProductResultDTO productResultDTO) throws Exception  {
		Pageable pageable = null;
		if(productDTO.page != null && productDTO.size != null) {
			pageable = PageRequest.of(productDTO.page.intValue(), productDTO.size.intValue());
			if(productDTO.page.intValue() > 0)
				productResultDTO.startIndex = productDTO.page.intValue() * productDTO.size.intValue() + 1;
			else
				productResultDTO.startIndex = productDTO.page.intValue() + 1;
		}
		return searchByFieldsSimple(productDTO, pageable,productResultDTO);
	}
	
	private List<Product> searchByFieldsSimple(ProductDTO productDTO, Pageable pageable,ProductResultDTO productResultDTO) throws Exception {
		if(!StringUtils.hasText(productDTO.name) && !StringUtils.hasText(productDTO.category)) {
			//SE TUTTI VUOTI RICERCA TUTTO
			productResultDTO.totalResult= productRepository.count();
			if(pageable!=null)
				return productRepository.findAll(pageable).getContent();
			else return productRepository.findAll();
		}
		else if(StringUtils.hasText(productDTO.name) && !StringUtils.hasText(productDTO.category)) {
			//SOLO NOME
			productResultDTO.totalResult = productRepository.countByNameContainingIgnoreCase(productDTO.name);
			if(pageable!=null)
				return productRepository.findByNameContainsIgnoreCase(pageable, productDTO.name);
			else return productRepository.findByNameContainsIgnoreCase(productDTO.name);
		}	
		else if(!StringUtils.hasText(productDTO.name) && StringUtils.hasText(productDTO.category)) {
			//SOLO CATEGORIA
			productResultDTO.totalResult = productRepository.countByCategoryContainingIgnoreCase(productDTO.category);
			if(pageable!=null)
				return productRepository.findByCategoryContainsIgnoreCase(pageable, productDTO.category);
			else return productRepository.findByCategoryContainsIgnoreCase(productDTO.category);
		}
		else {
			//COMPLETA
			productResultDTO.totalResult = productRepository.countByNameAndCategoryContainingIgnoreCase(productDTO.name.toUpperCase(), productDTO.category);
			if(pageable!=null)
				return productRepository.findByNameAndCategoryContainsIgnoreCase(pageable, productDTO.name.toUpperCase(), productDTO.category);
			else return productRepository.findByNameAndCategoryContainsIgnoreCase(productDTO.name.toUpperCase(), productDTO.category);
		}
	
	}
	
}
