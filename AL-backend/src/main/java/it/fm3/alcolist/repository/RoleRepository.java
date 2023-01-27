package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fm3.alcolist.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
	
}
