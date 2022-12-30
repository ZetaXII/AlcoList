package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fm3.alcolist.entity.Ordination;

public interface OrdinationRepository extends JpaRepository<Ordination, Long> {
	//List<Product> findByEmailAndDateDelete(String email,Date dataDelete);
	Ordination findByUuid(String uuid);
}
