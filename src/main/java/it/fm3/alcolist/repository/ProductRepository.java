package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.fm3.alcolist.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	//List<Product> findByEmailAndDateDelete(String email,Date dataDelete);
	Product findByUuid(String uuid);
}
