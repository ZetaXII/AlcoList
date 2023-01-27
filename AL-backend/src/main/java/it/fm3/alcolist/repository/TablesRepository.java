package it.fm3.alcolist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.fm3.alcolist.entity.Tables;

public interface TablesRepository extends JpaRepository<Tables, Long> {
		Tables findByUuid(String uuid);
		Tables findBynumber(Integer number);
		
		@Query("SELECT o.table FROM Ordination o WHERE o.uuid=:orderUuid")
		Tables findByOrderUuid(@Param("orderUuid") String orderUuid);
		
		//SOLO ISFREE
		List<Tables> findByIsFree(Boolean isFree);
		List<Tables> findByIsFree(Pageable pageable, Boolean isFree);
		int countByIsFree(Boolean isFree);
}
