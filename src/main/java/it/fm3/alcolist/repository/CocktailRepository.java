package it.fm3.alcolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import it.fm3.alcolist.entity.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
	//List<Product> findByEmailAndDateDelete(String email,Date dataDelete);
	Cocktail findByUuid(String uuid);
	List<Cocktail> findByName(String name);
	List<Cocktail> findByName(Pageable pageable, String name);
	List<Cocktail> findByFlavour(String flavour);
	List<Cocktail> findByFlavour(Pageable pageable, String flavour);
	List<Cocktail> findByIsAlcoholic(boolean alcoholic);
	List<Cocktail> findByIsAlcoholic(Pageable pageable, boolean alcoholic);
	List<Cocktail> findByNameAndFlavour(String name, String flavour);
	List<Cocktail> findByNameAndFlavour(Pageable pageable, String name, String flavour);
	List<Cocktail> findByNameAndIsAlcoholic(String name, boolean alcoholic);
	List<Cocktail> findByNameAndIsAlcoholic(Pageable pageable, String name, boolean alcholic);
	List<Cocktail> findByFlavourAndIsAlcoholic(String flavour, boolean alcholic);
	List<Cocktail> findByFlavourAndIsAlcoholic(Pageable pageable, String flavour, boolean alcholic);
	List<Cocktail> findByNameAndFlavourAndIsAlcoholic(String name, String flavour, boolean alcholic);
	List<Cocktail> findByNameAndFlavourAndIsAlcoholic(Pageable pageable, String name, String flavour, boolean alcholic);

	int countByNameContainingIgnoreCase(String name);
	int countByFlavourContainingIgnoreCase(String flavour);
	int countByIsAlcoholic(boolean alcoholic);
	int countByNameAndFlavourContainingIgnoreCase(String name, String flavour);
	int countByNameAndIsAlcoholic(String name, boolean alcoholic);
	int countByFlavourAndIsAlcoholic(String flavour, boolean alcoholic);
	int countByNameAndFlavourAndIsAlcoholic(String name, String flavour, boolean alcholic);
}
