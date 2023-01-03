package it.fm3.alcolist.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.TablesDTO;
import it.fm3.alcolist.DTO.TablesResultDTO;
import it.fm3.alcolist.entity.Tables;
import it.fm3.alcolist.repository.TablesRepository;

@Service
@Transactional
public class TablesService implements TablesServiceI{
	@Autowired
	TablesRepository tablesRepository;
	
	@Override
	public Tables add(TablesDTO t) throws Exception {
		Tables newTables= new Tables();
		this.buildTablesByTablesDTO(newTables,t);
		if(tablesRepository.findByUuid(t.uuid) != null)
			throw new Exception("product already exists");
		tablesRepository.save(newTables);
		 System.out.println("\n\n@@@@@@ NUOVO PRODOTTO: "+newTables);
		 return newTables;
	}

	@Override
	public Tables delete(String uuid) throws Exception {
		// TODO completare delete tables
		return null;
	}

	@Override
	public Tables update(TablesDTO t) throws Exception {
			Tables tablesToUpdate = tablesRepository.findByUuid(t.uuid);
			if(tablesToUpdate==null)
				throw new Exception("tables not found");
			this.buildTablesByTablesDTO(tablesToUpdate, t);
			return tablesToUpdate;
	}

	@Override
	public Tables get(String uuid) throws Exception {
		Tables t = tablesRepository.findByUuid(uuid);
		if(t != null)
			return t;
		else 
			throw new Exception("Table with uuid: " +uuid+ " not exist");
	}
	
	private void buildTablesByTablesDTO(Tables tables, TablesDTO tablesDTO) {
		tables.setNumber(tablesDTO.number);
		if(!StringUtils.hasText(tablesDTO.uuid))
			tables.setUuid(UUID.randomUUID().toString());
		else
			tables.setUuid(tablesDTO.uuid);
	}
	
	public List<Tables> getAll() throws Exception {
		return tablesRepository.findAll();
	}

	@Override
	public TablesResultDTO searchByFields(TablesDTO tDTO) throws Exception {
		TablesResultDTO tablesResult = new TablesResultDTO();
		
		tablesResult.tables = searchByFieldsRes(tDTO , tablesResult);
		
		if(tablesResult.tables.size() == 0 ) {
			tablesResult.totalResult=0;
			tablesResult.itemsPerPage =0;
			tablesResult.startIndex=0;
		}else {
			tablesResult.itemsPerPage = tablesResult.tables.size();
		}
		return tablesResult;
	}
	
	private List<Tables>  searchByFieldsRes(TablesDTO tDTO,TablesResultDTO tablesResultDTO) throws Exception  {
		Pageable pageable = null;
		if(tDTO.page != null && tDTO.size != null) {
			pageable = PageRequest.of(tDTO.page.intValue(), tDTO.size.intValue());
			if(tDTO.page.intValue() > 0)
				tablesResultDTO.startIndex = tDTO.page.intValue() * tDTO.size.intValue() + 1;
			else
				tablesResultDTO.startIndex = tDTO.page.intValue() + 1;
		}
		return searchByFieldsSimple(tDTO, pageable,tablesResultDTO);
	}
	
	private List<Tables> searchByFieldsSimple(TablesDTO tDTO, Pageable pageable,TablesResultDTO tablesResultDTO) throws Exception {
//		if(tDTO == null) {
//			//RICERCA DI TUTTI I TAVOLI
//			tablesResultDTO.totalResult = tablesRepository.count();
//			if(pageable!=null)
//				return tablesRepository.findAll(pageable).getContent();
//			else return tablesRepository.findAll();
//		}
//		return null;

		//RICERCA DI TUTTI I TAVOLI
		tablesResultDTO.totalResult = tablesRepository.count();
		if(pageable!=null)
			return tablesRepository.findAll(pageable).getContent();
		else return tablesRepository.findAll();
	}
}
