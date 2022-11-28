package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fm3.alcolist.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	//List<Product> findByEmailAndDateDelete(String email,Date dataDelete);
	Ingredient findByUuid(String uuid);
}
