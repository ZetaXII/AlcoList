package it.fm3.alcolist.service;

import it.fm3.alcolist.DTO.OrderedCocktailDTO;
import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.DTO.OrdinationStatusEnum;
import it.fm3.alcolist.entity.Ordination;

public interface OrdinationServiceI {
	Ordination create(OrdinationDTO c) throws Exception;
	Ordination delete(String uuid) throws Exception;
	Ordination updateStatus(String orderUuid,OrdinationStatusEnum status) throws Exception;
	Ordination get(String uuid) throws Exception;
	Ordination addCocktail(OrderedCocktailDTO oc) throws Exception;

}