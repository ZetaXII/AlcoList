package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.fm3.alcolist.entity.Tables;

public interface TablesRepository extends JpaRepository<Tables, Long> {
		Tables findByUuid(String uuid);
}
