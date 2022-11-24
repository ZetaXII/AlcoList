package it.fm3.alcolist.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.TablesDTO;
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
				throw new Exception("product not found");
			this.buildTablesByTablesDTO(tablesToUpdate, t);
			return tablesToUpdate;
	}

	@Override
	public Tables get(String uuid) {
		return tablesRepository.findByUuid(uuid);
	}
	
	private void buildTablesByTablesDTO(Tables tables, TablesDTO tablesDTO) {
		tables.setNumber(tablesDTO.number);
		tables.setSeats(tablesDTO.seats);
		if(!StringUtils.hasText(tablesDTO.uuid))
			tables.setUuid(UUID.randomUUID().toString());
		else
			tables.setUuid(tablesDTO.uuid);
	}

}
