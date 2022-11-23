package it.fm3.alcolist.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fm3.alcolist.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	List<UserAccount> findByEmailAndDateDelete(String email,Date dataDelete);
	UserAccount findByUuid(String uuid);
}
