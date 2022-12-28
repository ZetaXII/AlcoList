package it.fm3.alcolist.DTO;

import java.util.ArrayList;
import java.util.Date;

public class UserAccountDTO {
	
	public String name;
	public String surname;
	public ArrayList<String> roleList;//TODO l'utente ha un solo ruolo
	public String mainRole;
	public String password;
	public String email;
	public Date dateDelete;
	public String uuid;
	public Integer page;
	public Integer size;
}
