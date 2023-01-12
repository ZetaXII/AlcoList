package it.fm3.alcolist.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import it.fm3.alcolist.utils.RoleEnum;

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
