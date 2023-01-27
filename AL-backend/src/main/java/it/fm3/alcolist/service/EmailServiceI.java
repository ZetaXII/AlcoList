package it.fm3.alcolist.service;

public interface EmailServiceI {
	void sendSimpleMessage(String to, String subject, String text);
}
