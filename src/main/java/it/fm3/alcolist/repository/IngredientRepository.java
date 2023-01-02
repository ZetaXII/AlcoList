package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.fm3.alcolist.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	//List<Product> findByEmailAndDateDelete(String email,Date dataDelete);
	Ingredient findByUuid(String uuid);
	
	@Query("SELECT i FROM Ingredient i WHERE i.cocktail.uuid = :cocktailUuid AND i.product.uuid = :productUuid")
    Ingredient findByCocktailUuidAndProductUuid(@Param("cocktailUuid") String cocktailUuid, @Param("productUuid") String productUuid);
	
}
