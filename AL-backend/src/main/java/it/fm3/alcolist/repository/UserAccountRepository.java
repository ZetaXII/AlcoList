package it.fm3.alcolist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.fm3.alcolist.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	List<UserAccount> findByEmailAndDelateDateIsNull(String email);
	UserAccount findByUuid(String uuid);
	List<UserAccount> findByDelateDateIsNull();
	List<UserAccount> findByDelateDateIsNull(Pageable p);

}
