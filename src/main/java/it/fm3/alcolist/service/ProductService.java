package it.fm3.alcolist.service;


import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import it.fm3.alcolist.DTO.ProductDTO;
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
	
}
