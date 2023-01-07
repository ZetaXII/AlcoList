package it.fm3.alcolist.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.bouncycastle.util.encoders.Hex;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.fm3.alcolist.DTO.UserAccountDTO;
import it.fm3.alcolist.DTO.UserAccountResultDTO;
import it.fm3.alcolist.entity.Role;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.repository.RoleRepository;
import it.fm3.alcolist.repository.UserAccountRepository;

@Service
@Transactional
public class UserAccountService implements UserAccountServiceI{
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private String encriptPassword(String originalPassword) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String originalString=originalPassword;
		byte[] hash = digest.digest(
		  originalString.getBytes(StandardCharsets.UTF_8));
		String sha256hex = new String(Hex.encode(hash));
		return sha256hex;
	}
	public UserAccount add(UserAccountDTO userDto) throws Exception{
		UserAccount newUser= new UserAccount();
		this.buildUserAccountByUserAccountDTO(newUser,userDto);
		if(userAccountRepository.findByEmailAndDateDelete(userDto.email, null).size()>0)
			throw new Exception("user already exists");
		 userAccountRepository.save(newUser);
		 System.out.println("\n\n@@@@@@ NUOVO UTENTE: "+newUser);
		 return newUser;
	}

	@Override
	public UserAccount delete(String uuid) throws Exception {
		UserAccount userToDelete = userAccountRepository.findByUuid(uuid);
		if(userToDelete==null)
			throw new Exception("user not found with uuid: "+uuid);
		userToDelete.setDateDelete(new Date());
		return userToDelete;
	}

	@Override
	public UserAccount update(UserAccountDTO u) throws Exception {
		UserAccount usersToUpdate = userAccountRepository.findByUuid(u.uuid);
		if(usersToUpdate==null)
			throw new Exception("user not found");
		this.buildUserAccountByUserAccountDTO(usersToUpdate,u);
		return usersToUpdate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject login(UserAccountDTO u) throws Exception {
		//QQQ Integrare gestione token??? NO
		//come verrà gestito il login? 
		//il frontend controlla che un utente sia loggato?
		//come definisco un utente loggato?
		JSONObject response = new JSONObject();
		ArrayList<UserAccount> users = (ArrayList<UserAccount>) userAccountRepository.findByEmailAndDateDelete(u.email, null);
		if(users.size() == 1) { //check user email
			if(users.get(0).getPassword().equals(encriptPassword(u.password))) { 	//check password
				String jsonString = mapper.writeValueAsString(users.get(0));
				response = mapper.readValue(jsonString, JSONObject.class); 
			}
			else {
				response.put("error", "wrong pwd, try again");
			}
		}else { //user not found
			response.put("error", "User not found");
		}
		
		return response;
	}
	
	private void buildUserAccountByUserAccountDTO(UserAccount user,UserAccountDTO userDTO) throws Exception {

		user.setName(userDTO.name);
		user.setSurname(userDTO.surname);
		Role roleToSet=null;
		if(userDTO.roleList.size()>0) {
			ArrayList<Role> newRoleList = new ArrayList<Role>();
			for(String role : userDTO.roleList) {
				roleToSet=roleRepository.findByName(role);
				if (roleToSet==null)
					throw new Exception("role: "+role+" not found");
				newRoleList.add(roleToSet);
			}
			System.out.println(newRoleList);
			user.setRoles(newRoleList);
			System.out.println("setto: "+user.getRoles());
		}
		user.setMainRole(userDTO.mainRole);		
		if(StringUtils.hasText(userDTO.password))
			user.setPassword(this.encriptPassword(userDTO.password));
		user.setEmail(userDTO.email);
		if(userDTO.dateDelete!=null)
			user.setDateDelete(userDTO.dateDelete);
		if(!StringUtils.hasText(userDTO.uuid))
			user.setUuid(UUID.randomUUID().toString());
		else
			user.setUuid(userDTO.uuid);
		
	}

	public UserAccount get(String uuid) {
		return userAccountRepository.findByUuid(uuid);
	}
	
	@Override
	public UserAccountResultDTO searchByFields(UserAccountDTO userDTO) throws Exception {
		UserAccountResultDTO userResult = new UserAccountResultDTO();
		
		userResult.userAccounts = searchByFieldsRes(userDTO , userResult);
		
		if(userResult.userAccounts.size() == 0 ) {
			userResult.totalResult=0;
			userResult.itemsPerPage =0;
			userResult.startIndex=0;
		}else {
			userResult.itemsPerPage = userResult.userAccounts.size();
		}
		return userResult ;
	}
	
	private List<UserAccount>  searchByFieldsRes(UserAccountDTO userDTO,UserAccountResultDTO userResultDTO) throws Exception  {
		Pageable pageable = null;
		if(userDTO.page != null && userDTO.size != null) {
			pageable = PageRequest.of(userDTO.page.intValue(), userDTO.size.intValue());
			if(userDTO.page.intValue() > 0)
				userResultDTO.startIndex = userDTO.page.intValue() * userDTO.size.intValue() + 1;
			else
				userResultDTO.startIndex = userDTO.page.intValue() + 1;
		}
		return searchByFieldsSimple(userDTO, pageable,userResultDTO);
	}
	
	private List<UserAccount> searchByFieldsSimple(UserAccountDTO userDTO, Pageable pageable,UserAccountResultDTO userResultDTO) throws Exception {
		/*
		if(StringUtils.hasText(userDTO.name) && !StringUtils.hasText(userDTO.description)) {
			userResultDTO.totalResult = cor.countByNameContainingIgnoreCase(userDTO.name);
			return .findByNameContainingIgnoreCase(userDTO.name, pageable);
		}	
		else {
			userResultDTO.totalResult = countByFieldsSimple(commonObjectDTO);
			return cor.findAll();
		}*/
		userResultDTO.totalResult= userAccountRepository.count();
		if(pageable!=null)
			return userAccountRepository.findAll(pageable).getContent();
		else return userAccountRepository.findAll();
		
	}
	@Override
	public List<UserAccount> getAll() {
		return userAccountRepository.findAll();
	}
}
