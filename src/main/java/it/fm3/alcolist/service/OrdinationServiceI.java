package it.fm3.alcolist.service;

import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.entity.Ordination;

public interface OrdinationServiceI {
	Ordination add(OrdinationDTO c) throws Exception;
	Ordination delete(String uuid) throws Exception;
	Ordination update(OrdinationDTO c) throws Exception;
	Ordination get(String uuid);
}