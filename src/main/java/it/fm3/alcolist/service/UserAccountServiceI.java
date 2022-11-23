package it.fm3.alcolist.service;

import org.json.simple.JSONObject;

import it.fm3.alcolist.DTO.UserAccountDTO;
import it.fm3.alcolist.entity.UserAccount;

public interface UserAccountServiceI {
	
	UserAccount add(UserAccountDTO u) throws Exception;
	UserAccount delete(String uuid) throws Exception;
	UserAccount update(UserAccountDTO u) throws Exception;
	JSONObject login(UserAccountDTO u) throws Exception;
	UserAccount get(String uuid);
}
