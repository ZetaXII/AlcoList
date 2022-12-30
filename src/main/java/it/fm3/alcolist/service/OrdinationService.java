package it.fm3.alcolist.service;


import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.entity.Ordination;
import it.fm3.alcolist.entity.Tables;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.repository.OrdinationRepository;
import it.fm3.alcolist.repository.TablesRepository;
import it.fm3.alcolist.utils.RoleEnum;

@Service
@Transactional
public class OrdinationService implements OrdinationServiceI{
	
	public static final RoleEnum permission=RoleEnum.WAITER;
	
	@Autowired
	OrdinationRepository ordinationRepository;
	@Autowired
	TablesRepository tablesRepository;
	@Autowired
	UserAccountService userAccountService;

	@Override
	public Ordination add(OrdinationDTO ordinationDto) throws Exception {
		//Ordination newOrdination= new Ordination();
		//this.buildOrdinationByDTO(newOrdination, ordinationDto);
		//ordinationRepository.save(newOrdination);
		return null;
	}

	@Override
	public Ordination delete(String uuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordination update(OrdinationDTO c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordination get(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void buildOrdinationByDTO(Ordination ordination, OrdinationDTO ordinationDTO) throws Exception {
		if(ordinationDTO.dateCreation == null) 
			ordination.setDateCreation(new Date());
		if(ordinationDTO.dateLastModified == null) 
			ordination.setDateLastModified(new Date());
		if(StringUtils.hasText(ordinationDTO.tableUuid)) {
			Tables t=tablesRepository.findByUuid(ordinationDTO.tableUuid);
			if(t==null)throw new Exception("Table with uuid: "+ordinationDTO.tableUuid+" not found");
			ordination.setTable(t);
		}
		if(!StringUtils.hasText(ordinationDTO.uuid))
			ordination.setUuid(UUID.randomUUID().toString());
		else
			ordination.setUuid(ordinationDTO.uuid);
		if(ordinationDTO.total!=null)
			ordination.setTotal(ordinationDTO.total);
		if(StringUtils.hasText(ordinationDTO.createdByUserUuid)){
			UserAccount u=userAccountService.get(ordinationDTO.createdByUserUuid);
			if(u==null)throw new Exception("User with uuid: "+ordinationDTO.createdByUserUuid+" not found");
			ordination.setCreatedBy(u);
		}
		if(StringUtils.hasText(ordinationDTO.deliveredBy)){
			UserAccount u=userAccountService.get(ordinationDTO.deliveredBy);
			if(u==null)throw new Exception("User with uuid: "+ordinationDTO.deliveredBy+" not found");
			ordination.setDeliveredBy(u);
		}
		if(StringUtils.hasText(ordinationDTO.executedBy)){
			UserAccount u=userAccountService.get(ordinationDTO.executedBy);
			if(u==null)throw new Exception("User with uuid: "+ordinationDTO.executedBy+" not found");
			ordination.setExecutedBy(u);
		}
	}
		
}
