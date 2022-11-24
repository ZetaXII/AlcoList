package it.fm3.alcolist.service;

import it.fm3.alcolist.entity.Tables;
import it.fm3.alcolist.DTO.TablesDTO;

public interface TablesServiceI {
	Tables add(TablesDTO t) throws Exception;
	Tables delete(String uuid) throws Exception;
	Tables update(TablesDTO p) throws Exception;
	Tables get(String uuid);
}