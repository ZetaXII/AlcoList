package it.fm3.alcolist.service;
import it.fm3.alcolist.DTO.ProductDTO;
import it.fm3.alcolist.DTO.ProductResultDTO;
import it.fm3.alcolist.entity.Product;

public interface ProductServiceI {
	Product add(ProductDTO p) throws Exception;
	Product delete(String uuid) throws Exception;
	Product update(ProductDTO p) throws Exception;
	Product get(String uuid);
	ProductResultDTO searchByFields(ProductDTO productDTO) throws Exception;
}
