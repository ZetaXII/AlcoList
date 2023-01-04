package it.fm3.alcolist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.fm3.alcolist.DTO.OrdinationStatusEnum;
import it.fm3.alcolist.entity.Ordination;

public interface OrdinationRepository extends JpaRepository<Ordination, Long> {
	//List<Product> findByEmailAndDateDelete(String email,Date dataDelete);
	Ordination findByUuid(String uuid);
	
	@Query("SELECT o FROM Ordination o WHERE o.createdBy.uuid=:useruuid")
	List<Ordination> findByCreatedBy(@Param("useruuid") String createdByUserUuid);
	@Query("SELECT o FROM Ordination o WHERE o.createdBy.uuid=:useruuid")
	List<Ordination> findByCreatedBy(Pageable pageable, @Param("useruuid") String createdByUserUuid);
	@Query("SELECT COUNT(o) FROM Ordination o WHERE o.createdBy.uuid=:useruuid")
	int countByCreatedBy(@Param("useruuid") String createdByUserUuid);
	
	
	@Query("SELECT o FROM Ordination o WHERE o.deliveredBy.uuid=:useruuid")
	List<Ordination> findByDeliveredBy(@Param("useruuid") String deliveredBy);
	@Query("SELECT o FROM Ordination o WHERE o.deliveredBy.uuid=:useruuid")
	List<Ordination> findByDeliveredBy(Pageable pageable, @Param("useruuid") String deliveredBy);
	@Query("SELECT COUNT(o) FROM Ordination o WHERE o.deliveredBy.uuid=:useruuid")
	int countByDeliveredBy(@Param("useruuid") String deliveredBy);
	
	@Query("SELECT o FROM Ordination o WHERE o.executedBy.uuid=:useruuid")
	List<Ordination> findByExecutedBy(@Param("useruuid") String executedBy);
	@Query("SELECT o FROM Ordination o WHERE o.executedBy.uuid=:useruuid")
	List<Ordination> findByExecutedBy(Pageable pageable, @Param("useruuid") String executedBy);
	@Query("SELECT COUNT(o) FROM Ordination o WHERE o.executedBy.uuid=:useruuid")
	int countByExecutedBy(@Param("useruuid") String executedBy);
	
	@Query("SELECT o FROM Ordination o WHERE o.status=:status")
	List<Ordination> findByStatus(@Param("status") OrdinationStatusEnum status);
	@Query("SELECT o FROM Ordination o WHERE o.status=:status")
	List<Ordination> findByStatus(Pageable pageable, @Param("status") OrdinationStatusEnum status);
	@Query("SELECT COUNT(o) FROM Ordination o WHERE o.status=:status")
	int countByStatus(@Param("status") OrdinationStatusEnum status);
	
}
