package it.fm3.alcolist.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

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
	@Autowired
	private EmailServiceI emailService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private String encriptPassword(String originalPassword) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String originalString=originalPassword;
		byte[] hash = digest.digest(
		  originalString.getBytes(StandardCharsets.UTF_8));
		String sha256hex = new String(Hex.encode(hash));
		return sha256hex;
	}
	
	private char[] generatePassword(int length) {
	      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "!@#$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      char[] password = new char[length];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 4; i< length ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      return password;
	   }
	
	private String generateRandomPassword() throws Exception {
		char[] pwd = this.generatePassword(8);
		return pwd.toString();
	}
	
	public UserAccount add(UserAccountDTO userDto) throws Exception{
		UserAccount newUser= new UserAccount();
		userDto.password = this.generateRandomPassword();
		this.buildUserAccountByUserAccountDTO(newUser,userDto);
		if(userAccountRepository.findByEmail(userDto.email).size()>0)
			throw new Exception("user already exists");
		String msg = "Ciao, benvenuto nel nostro Team!\n\nLa tua password è: " + userDto.password + "\n\nSaluti, Team AlcoList.";
		String mail = userDto.email;
		//String msg = "Ciao, sappiamo che lei fa parte del nostro Team!\n\n Con la presente le volevamo ricordare che bisgna terminare al più presto. \n\nSaluti, Team AlcoList.";
		emailService.sendSimpleMessage(mail, "Invio password", msg);
		userAccountRepository.save(newUser);
		return newUser;
	}

	@Override
	public UserAccount delete(String uuid) throws Exception {
		UserAccount userToDelete = userAccountRepository.findByUuid(uuid);
		if(userToDelete==null)
			throw new Exception("user not found with uuid: "+uuid);
		userAccountRepository.delete(userToDelete);
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
		//UPGRADE Integrare gestione token??? NO non ora
		//come verrà gestito il login? appezzottato
		//il frontend controlla che un utente sia loggato? si
		//come definisco un utente loggato? non lo definisco
		JSONObject response = new JSONObject();
		ArrayList<UserAccount> users = (ArrayList<UserAccount>) userAccountRepository.findByEmail(u.email);
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
		this.checkNameField(userDTO.name);
		this.checkSurnameField(userDTO.surname);
		this.checkRoleField(userDTO.mainRole);
		this.checkEmailField(userDTO.email);
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
	
	private boolean checkNameField(String name) throws Exception {
		boolean check = true;
		if(this.checkIfStringContainsDigit(name)) {
			check = false;
			throw new Exception("The name must consist of only characters");
		}
		if(name.length() < 2) {
			check = false;
			throw new Exception("The length of the name must be at least 2 characters");
		}
		if(name.length() > 30) {
			check = false;
			throw new Exception("The length of the name should be 30 characters maximum");
		}
		return check;
	}
	
	private boolean checkSurnameField(String name) throws Exception {
		boolean check = true;
		if(this.checkIfStringContainsDigit(name)) {
			check = false;
			throw new Exception("The surname must consist of only characters");
		}
		if(name.length() < 2) {
			check = false;
			throw new Exception("The length of the surname must be at least 2 characters");
		}
		if(name.length() > 30) {
			check = false;
			throw new Exception("The length of the surname should be 30 characters maximum");
		}
		return check;
	}
	
	private boolean checkRoleField(String role) throws Exception {
		boolean check = true;
		if(this.checkIfStringContainsDigit(role)) {
			check = false;
			throw new Exception("The role must consist of only characters");
		}
		if(role.length() < 5) {
			check = false;
			throw new Exception("The length of the role must be at least 2 characters");
		}
		if(role.length() > 30) {
			check = false;
			throw new Exception("The length of the role should be 30 characters maximum");
		}
		return check;
	}
	
	public boolean checkEmailField(String email) throws Exception
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if(email == null)
        	return false;
        if(!pat.matcher(email).matches())
        	throw new Exception("Email format not valid");
        
        return true;
    }
	
	public boolean checkIfStringContainsDigit(String passCode){
	      for (int i = 0; i < passCode.length(); i++) {
	        if(Character.isDigit(passCode.charAt(i))) {
	            return true;
	        }
	      }
	      return false;
	}
}
