package it.fm3.alcolist.DTO;

import java.util.List;

import it.fm3.alcolist.entity.Product;

public class ProductResultDTO {

	public long totalResult;
	public long startIndex;
	public long itemsPerPage;
	public List<Product> product;

}
