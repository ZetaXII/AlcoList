package it.fm3.alcolist.service;

import java.util.List;

import it.fm3.alcolist.DTO.MessageDTO;
import it.fm3.alcolist.DTO.OrderedCocktailDTO;
import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.DTO.OrdinationResultDTO;
import it.fm3.alcolist.DTO.OrdinationStatusEnum;
import it.fm3.alcolist.entity.Ordination;

public interface OrdinationServiceI {
	Ordination create(OrdinationDTO c) throws Exception;
	Ordination delete(String uuid) throws Exception;
	Ordination updateStatus(String orderUuid,OrdinationStatusEnum status,MessageDTO msg) throws Exception;
	Ordination get(String uuid) throws Exception;
	Ordination addCocktail(OrderedCocktailDTO oc) throws Exception;
	Ordination removeCocktail(OrderedCocktailDTO oc) throws Exception;
	OrdinationResultDTO searchByFields (OrdinationDTO o) throws Exception;
	Ordination update(String uuidOrder, String uuidTable) throws Exception;
	List<Ordination> findOpenOrdinationForTableUuid(String tableUuid);

}