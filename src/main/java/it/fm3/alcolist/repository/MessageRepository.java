package it.fm3.alcolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fm3.alcolist.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {	
}
