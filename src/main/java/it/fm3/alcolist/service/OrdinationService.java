package it.fm3.alcolist.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.fm3.alcolist.DTO.OrderedCocktailDTO;
import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.DTO.OrdinationResultDTO;
import it.fm3.alcolist.DTO.OrdinationStatusEnum;
import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.entity.Ingredient;
import it.fm3.alcolist.entity.OrderedCocktail;
import it.fm3.alcolist.entity.Ordination;
import it.fm3.alcolist.entity.Product;
import it.fm3.alcolist.entity.Tables;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.repository.IngredientRepository;
import it.fm3.alcolist.repository.OrderedCocktailRepository;
import it.fm3.alcolist.repository.OrdinationRepository;
import it.fm3.alcolist.repository.TablesRepository;
import it.fm3.alcolist.utils.RoleEnum;

@Service
@Transactional
public class OrdinationService implements OrdinationServiceI{
	
	public static final RoleEnum permission=RoleEnum.WAITER;
	
	@Autowired
	private OrdinationRepository ordinationRepository;
	@Autowired
	private TablesRepository tablesRepository;
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private CocktailServiceI cocktailService;
	@Autowired
	private OrderedCocktailRepository orderedCocktailRepository;
	@Autowired
	private IngredientRepository ingredientRepository;


	@Override
	public Ordination create(OrdinationDTO ordinationDto) throws Exception {
		ordinationDto.status=OrdinationStatusEnum.CREATED;
		Ordination newOrdination= new Ordination();
		Tables t = tablesRepository.findByUuid(ordinationDto.tableUuid);
		t.setIsFree(false);
		this.buildOrdinationByDTO(newOrdination, ordinationDto);
		ordinationRepository.save(newOrdination);
		return newOrdination;
	}

	@Override
	public Ordination delete(String uuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordination updateStatus(String orderUuid,OrdinationStatusEnum status) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordination get(String uuid) throws Exception {
		Ordination o=this.ordinationRepository.findByUuid(uuid);
		if(o==null) throw new Exception("Order with uuid "+uuid+" not exists");
		return o;
	}
	
	private void buildOrdinationByDTO(Ordination ordination, OrdinationDTO ordinationDTO) throws Exception {
		if(ordinationDTO.status == null) 
			ordination.setStatus(ordinationDTO.status);
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
			System.out.println("recupero l'utente");
			UserAccount u=userAccountService.get(ordinationDTO.createdByUserUuid);
			if(u==null)throw new Exception("User with uuid: "+ordinationDTO.createdByUserUuid+" not found");
			System.out.println("ho trovato "+u);
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
	
	@Override
	//FIXME CALCOLARE IL COSTO TOTALE DEL ORDINE
	public Ordination addCocktail(OrderedCocktailDTO ord) throws Exception {
		OrderedCocktail ocSearch = orderedCocktailRepository.findByOrderUuidAndCocktailUuid(ord.ordinationUuid, ord.cocktailUuid);
		System.out.println("\n\n@@@@@@ ocSearch -> "+ocSearch);
		OrderedCocktail ocRes;
		if(ocSearch==null) {
			Ordination o=this.get(ord.ordinationUuid);
			System.out.println("\n\n@@@@@@ ocSearch -> getOrder-> "+o);

			Cocktail c=cocktailService.get(ord.cocktailUuid);
			System.out.println("\n\n@@@@@@ ocSearch -> Cocktail-> "+c);

			OrderedCocktail oc= new OrderedCocktail();
			oc.setCocktail(c);
			oc.setOrdination(o);
			oc.setQuantity(1);
			orderedCocktailRepository.save(oc);
			ocRes=oc;
		}else {
			ocRes=ocSearch;
			ocRes.setQuantity(ocRes.getQuantity()+1);
		}
		System.out.println("\n\n@@@@@@ Ordination -> "+ocRes.getOrdination());
		this.useProductForCocktail(ord.cocktailUuid);
		return ocRes.getOrdination();
	}
	
	private void useProductForCocktail(String cocoktailUuid) throws Exception{
		System.out.println("sono in useProductForCocktail");
		Set<Ingredient> ingredients = cocktailService.getIngredients(cocoktailUuid);
		Iterator<Ingredient> i = ingredients.iterator();	
		while(i.hasNext()) {
			   Ingredient ingredient=i.next();
			   if(ingredient.getQuantity()!=null) {
				   this.reduceProductQuantity(ingredient.getProduct(), ingredient.getQuantity());
				   ingredientRepository.deactivateFinischedIngredients(ingredient.getProduct().getUuid());
			   }
			}
	}
	
	private void reduceProductQuantity(Product p,int quantity) throws Exception {
		if(p.getMl()>=quantity) {
			p.setMl(p.getMl()-quantity);
			if(p.getMl()==0)p.setPresent(false);
		}else throw new Exception("quantity not sufficient for product "+p.getCategory()+" "+p.getName());
	}

	//FIXME DA DEFINIRE API PER PASSAGGIO STATO DA CREATED A SENT 
	
	@Override
	public OrdinationResultDTO searchByFields(OrdinationDTO ordinationDTO) throws Exception {
		OrdinationResultDTO ordinationResult = new OrdinationResultDTO();
		
		ordinationResult.ordinations = searchByFieldsRes(ordinationDTO , ordinationResult);
		
		if(ordinationResult.ordinations.size() == 0 ) {
			ordinationResult.totalResult=0;
			ordinationResult.itemsPerPage =0;
			ordinationResult.startIndex=0;
		}else {
			ordinationResult.itemsPerPage = ordinationResult.ordinations.size();
		}
		return ordinationResult;
	}
	
	private List<Ordination>  searchByFieldsRes(OrdinationDTO ordinationDTO, OrdinationResultDTO ordinationResultDTO) throws Exception  {
		Pageable pageable = null;
		if(ordinationDTO.page != null && ordinationDTO.size != null) {
			pageable = PageRequest.of(ordinationDTO.page.intValue(), ordinationDTO.size.intValue());
			if(ordinationDTO.page.intValue() > 0)
				ordinationResultDTO.startIndex = ordinationDTO.page.intValue() * ordinationDTO.size.intValue() + 1;
			else
				ordinationResultDTO.startIndex = ordinationDTO.page.intValue() + 1;
		}
		return searchByFieldsSimple(ordinationDTO, pageable,ordinationResultDTO);
	}
	
	private List<Ordination> searchByFieldsSimple(OrdinationDTO ordinationDTO, Pageable pageable, OrdinationResultDTO ordinationResultDTO) throws Exception {
		if(StringUtils.hasText(ordinationDTO.createdByUserUuid) && !StringUtils.hasText(ordinationDTO.deliveredBy) && !StringUtils.hasText(ordinationDTO.executedBy) && (ordinationDTO.status)==null) {
			//SOLO CREATED BY
			ordinationResultDTO.totalResult= ordinationRepository.countByCreatedBy(ordinationDTO.createdByUserUuid);
			if(pageable!=null)
				return ordinationRepository.findByCreatedBy(pageable, ordinationDTO.createdByUserUuid);
			else return ordinationRepository.findByCreatedBy(ordinationDTO.createdByUserUuid);
		}
		if(!StringUtils.hasText(ordinationDTO.createdByUserUuid) && StringUtils.hasText(ordinationDTO.deliveredBy) && !StringUtils.hasText(ordinationDTO.executedBy) && (ordinationDTO.status)==null) {
			//SOLO DELIVERED BY
			ordinationResultDTO.totalResult= ordinationRepository.countByDeliveredBy(ordinationDTO.deliveredBy);
			if(pageable!=null)
				return ordinationRepository.findByDeliveredBy(pageable, ordinationDTO.deliveredBy);
			else return ordinationRepository.findByDeliveredBy(ordinationDTO.deliveredBy);
		}
		if(!StringUtils.hasText(ordinationDTO.createdByUserUuid) && !StringUtils.hasText(ordinationDTO.deliveredBy) && StringUtils.hasText(ordinationDTO.executedBy) && (ordinationDTO.status)==null) {
			//SOLO EXECUTED BY
			ordinationResultDTO.totalResult= ordinationRepository.countByExecutedBy(ordinationDTO.executedBy);
			if(pageable!=null)
				return ordinationRepository.findByExecutedBy(pageable, ordinationDTO.executedBy);
			else return ordinationRepository.findByExecutedBy(ordinationDTO.executedBy);
		}
		if(!StringUtils.hasText(ordinationDTO.createdByUserUuid) && !StringUtils.hasText(ordinationDTO.deliveredBy) && !StringUtils.hasText(ordinationDTO.executedBy) && (ordinationDTO.status)!=null) {
		//SOLO STATUS
		ordinationResultDTO.totalResult= ordinationRepository.countByStatus(ordinationDTO.status);
		if(pageable!=null)
			return ordinationRepository.findByStatus(pageable, ordinationDTO.status);
		else return ordinationRepository.findByStatus(ordinationDTO.status);
		}

		List<Ordination> voidList = new ArrayList<>();
		return voidList;
	}
}
