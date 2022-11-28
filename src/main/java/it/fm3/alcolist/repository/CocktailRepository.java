package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fm3.alcolist.entity.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
	//List<Product> findByEmailAndDateDelete(String email,Date dataDelete);
	Cocktail findByUuid(String uuid);
}
