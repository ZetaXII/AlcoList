package it.fm3.alcolist.DTO;

import java.util.ArrayList;
import java.util.Objects;

public class UserAccountDTO {
	
	public String name;
	public String surname;
	public ArrayList<String> roleList;
	public String mainRole;
	public String password;
	public String email;
	public String uuid;
	public Integer page;
	public Integer size;
	
	public UserAccountDTO(String name, String surname, ArrayList<String> roleList, String mainRole, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.roleList = roleList;
		this.mainRole = mainRole;
		this.email = email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, mainRole, name, roleList, surname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccountDTO other = (UserAccountDTO) obj;
		boolean res= Objects.equals(email, other.email) && Objects.equals(mainRole, other.mainRole)
				&& Objects.equals(name, other.name) && Objects.equals(roleList, other.roleList)
				&& Objects.equals(surname, other.surname);
	System.out.println("@@@@@@@@@@sto facendo l'equals e restituisce: "+res);
	return res;
	}
	
	
}
