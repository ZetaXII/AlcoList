package it.fm3.alcolist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.fm3.alcolist.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	//List<Product> findByEmailAndDateDelete(String email,Date dataDelete);
	Product findByUuid(String uuid);
	
	List<Product> findByNameContainsIgnoreCase(String name);
	List<Product> findByNameContainsIgnoreCase(Pageable pageable, String name);
	int countByNameContainingIgnoreCase(String name);
	
	List<Product> findByCategoryContainsIgnoreCase(String category);
	List<Product> findByCategoryContainsIgnoreCase(Pageable pageable, String category);
	int countByCategoryContainingIgnoreCase(String name);
	
	@Query("SELECT p FROM Product p WHERE UPPER(p.name) LIKE %:name% AND p.category = :category")
	List<Product> findByNameAndCategoryContainsIgnoreCase(@Param("name") String name, @Param("category") String category);
	List<Product> findByNameAndCategoryContainsIgnoreCase(Pageable pageable, @Param("name") String name, @Param("category") String category);
	@Query("SELECT COUNT(p) FROM Product p WHERE UPPER(p.name) LIKE %:name% AND p.category = :category")
	int countByNameAndCategoryContainingIgnoreCase(@Param("name") String name, @Param("category") String category);
}
