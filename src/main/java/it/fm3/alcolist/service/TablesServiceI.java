package it.fm3.alcolist.service;

import java.util.List;

import it.fm3.alcolist.DTO.TablesDTO;
import it.fm3.alcolist.DTO.TablesResultDTO;
import it.fm3.alcolist.entity.Tables;

public interface TablesServiceI {
	Tables add(TablesDTO t) throws Exception;
	Tables delete(String uuid) throws Exception;
	Tables update(TablesDTO t) throws Exception;
	Tables get(String uuid) throws Exception;
	List<Tables> getAll() throws Exception;
	TablesResultDTO searchByFields(TablesDTO t) throws Exception;
}