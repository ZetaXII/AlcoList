package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.fm3.alcolist.entity.OrderedCocktail;

public interface OrderedCocktailRepository extends JpaRepository<OrderedCocktail, Long> {
	/*
		@Query("SELECT c FROM Cocktail c WHERE UPPER(c.name) LIKE %:name% AND c.flavour = :flavour")
	List<Cocktail> findByNameAndFlavourContainsIgnoreCase(@Param("name") String name, @Param("flavour") String flavour);

	*/
	@Query("SELECT oc FROM OrderedCocktail oc WHERE oc.cocktail.uuid =:cocktailUuid AND oc.ordination.uuid = :ordinationUuid")
	OrderedCocktail findByOrderUuidAndCocktailUuid(@Param("ordinationUuid")String ordinationUuid,@Param("cocktailUuid")String cocktailUuid);

	
	
}
