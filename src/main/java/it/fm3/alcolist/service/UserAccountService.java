package it.fm3.alcolist.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.UserAccountDTO;
import it.fm3.alcolist.entity.Role;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.repository.RoleRepository;
import it.fm3.alcolist.repository.UserAccountRepository;

@Service
@Transactional
public class UserAccountService implements UserAccountServiceI{
	@Autowired
	UserAccountRepository userAccountRepository;
	@Autowired
	RoleRepository roleRepository;
	
	private String encriptPassword(String originalPassword) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String originalString=originalPassword;
		byte[] hash = digest.digest(
		  originalString.getBytes(StandardCharsets.UTF_8));
		String sha256hex = new String(Hex.encode(hash));
		return sha256hex;
	}
	public UserAccount add(UserAccountDTO userDto) throws Exception{
		//QQQ è giusto avere user account se non ho un istanza di userAccount sul db?
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

	@Override
	public UserAccount login(UserAccountDTO u) throws Exception {
		//QQQ Integrare gestione token???
		//come verrà gestito il login? 
		//il frontend controlla che un utente sia loggato?
		//come definisco un utente loggato?
		
		return null;
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
	/*
	public UserAccountResultDTO searchByFieldsPageable(UserAccountDTO userDTO,UserAccountResultDTO userResultDTO) {
		Pageable pageable = null;
		if(userDTO.page != null && userDTO.size != null) {
			pageable = PageRequest.of(userDTO.page.intValue(), userDTO.size.intValue());
			
			if(userDTO.page.intValue() > 0)
				userResultDTO.startIndex = userDTO.page.intValue() * userDTO.size.intValue() + 1;
			else
				userResultDTO.startIndex = userDTO.page.intValue() + 1;
		}
	}
	*/
	//private List<UserAccount> searchByFields()
	
	//---------------------------
	/*
	private List<CommonObject> searchByFieldsSimple(CommonObjectDTO commonObjectDTO, Pageable pageable,CommonObjectResultDTO cord) throws Exception {
		if(StringUtils.hasText(commonObjectDTO.name) && !StringUtils.hasText(commonObjectDTO.description)) {
			cord.totalResult = cor.countByNameContainingIgnoreCase(commonObjectDTO.name);
			return cor.findByNameContainingIgnoreCase(commonObjectDTO.name, pageable);
		}	
		else if(!StringUtils.hasText(commonObjectDTO.name) && StringUtils.hasText(commonObjectDTO.description)) {
			cord.totalResult = cor.countByDescriptionContainingIgnoreCase(commonObjectDTO.description);
			return cor.findByDescriptionContainingIgnoreCase(commonObjectDTO.description, pageable);
		}
		else if(StringUtils.hasText(commonObjectDTO.name) && StringUtils.hasText(commonObjectDTO.description)) {
			cord.totalResult = cor.countByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(commonObjectDTO.name, commonObjectDTO.description);
			return cor.findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(commonObjectDTO.name, commonObjectDTO.description, pageable);
		}else {
			if(pageable != null) {
				cord.totalResult = countByFieldsSimple(commonObjectDTO);
				return cor.findAll(pageable).getContent();
			}else {
				cord.totalResult = countByFieldsSimple(commonObjectDTO);
				return cor.findAll();
			}
		}
	}
	
	@Override
	public long count() {
		return cor.count();
	}
	
	@Override
	public long countByObjectType(CommonObjectDTO commonObjectDTO) throws Exception {
		return cor.countByObjectType(otr.findByName(commonObjectDTO.objectTypeName));
	}
	
	@Override
	public long countByFields(CommonObjectDTO commonObjectDTO) throws Exception {
		if(StringUtils.hasText(commonObjectDTO.objectTypeName)) {
			CommonObjectType objectType = otr.findByName(commonObjectDTO.objectTypeName);
			if(objectType != null && objectType.getId() > 0) {
				if(StringUtils.hasText(commonObjectDTO.name) && !StringUtils.hasText(commonObjectDTO.description))
					return cor.countByNameContainingIgnoreCaseAndObjectType(commonObjectDTO.name, objectType);
				else if(!StringUtils.hasText(commonObjectDTO.name) && StringUtils.hasText(commonObjectDTO.description))
					return cor.countByDescriptionContainingIgnoreCaseAndObjectType(commonObjectDTO.description, objectType);
				else if(StringUtils.hasText(commonObjectDTO.name) && StringUtils.hasText(commonObjectDTO.description))
					return cor.countByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndObjectType(commonObjectDTO.name, commonObjectDTO.description, objectType);
				else 
					return cor.countByObjectType(objectType);
			} else
				return 0;
		}
		return countByFieldsSimple(commonObjectDTO);
	}

	private long countByFieldsSimple(CommonObjectDTO commonObjectDTO) throws Exception {
		if(StringUtils.hasText(commonObjectDTO.name) && !StringUtils.hasText(commonObjectDTO.description))
			return cor.countByNameContainingIgnoreCase(commonObjectDTO.name);
		else if(!StringUtils.hasText(commonObjectDTO.name) && StringUtils.hasText(commonObjectDTO.description))
			return cor.countByDescriptionContainingIgnoreCase(commonObjectDTO.description);
		else if(StringUtils.hasText(commonObjectDTO.name) && StringUtils.hasText(commonObjectDTO.description))
			return cor.countByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(commonObjectDTO.name, commonObjectDTO.description);
		else 
			return cor.count();
	}
*/
}
