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

import it.fm3.alcolist.DTO.MessageDTO;
import it.fm3.alcolist.DTO.OrderedCocktailDTO;
import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.DTO.OrdinationResultDTO;
import it.fm3.alcolist.DTO.TablesDTO;
import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.entity.Ingredient;
import it.fm3.alcolist.entity.Message;
import it.fm3.alcolist.entity.OrderedCocktail;
import it.fm3.alcolist.entity.Ordination;
import it.fm3.alcolist.entity.Product;
import it.fm3.alcolist.entity.Tables;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.repository.IngredientRepository;
import it.fm3.alcolist.repository.MessageRepository;
import it.fm3.alcolist.repository.OrderedCocktailRepository;
import it.fm3.alcolist.repository.OrdinationRepository;
import it.fm3.alcolist.repository.UserAccountRepository;
import it.fm3.alcolist.utils.OrdinationStatusEnum;
import it.fm3.alcolist.utils.RoleEnum;

@Service
@Transactional
public class OrdinationService implements OrdinationServiceI{
	
	public static final RoleEnum permission=RoleEnum.WAITER;
	
	@Autowired
	private OrdinationRepository ordinationRepository;
	@Autowired
	private TablesServiceI tablesService;
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private CocktailServiceI cocktailService;
	@Autowired
	private OrderedCocktailRepository orderedCocktailRepository;
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private MessageRepository messageRepository;


	@Override
	public Ordination create(OrdinationDTO ordinationDto) throws Exception {
		ordinationDto.status=OrdinationStatusEnum.CREATED;
		Ordination newOrdination= new Ordination();
		Tables t = tablesService.get(ordinationDto.tableUuid);
		t.setIsFree(false);
		this.buildOrdinationByDTO(newOrdination, ordinationDto);
		ordinationRepository.save(newOrdination);
		return newOrdination;
	}

	@Override
	public Ordination delete(String uuid) throws Exception {
		Ordination order= this.get(uuid);
		if(!order.getStatus().isEditable() || order.getStatus().isRequireMessage())
			throw new Exception("Status das not permit delete of order");
		else
			ordinationRepository.delete(order);
		return null;
	}

	@Override
	
	public Ordination updateStatus(String orderUuid,OrdinationStatusEnum status,MessageDTO msg) throws Exception {
		Ordination order=this.get(orderUuid);
		if(order.getNumbersOfCocktails() == 0)
			throw new Exception("Numbers of cocktails must be at least 1");
		if(order.getStatus()==status)
			throw new Exception("ordination has already the state "+status.name());
		if(!order.getStatus().isEditable() && !(status.ordinal()==order.getStatus().ordinal()-1 || status.ordinal()==order.getStatus().ordinal()+1))
			throw new Exception("Illegal update status");
		else {
			//sono nel caso editable a true
			if(order.getStatus().isRequireMessage()&&( msg==null||StringUtils.hasText(msg.note))) 
				throw new Exception("Note is required");
		}
		if(msg.userUuid==null)
			throw new Exception("userUuid is required");
		String msgStatus="status  "+order.getStatus().getLabel()+" -> "+status.getLabel();
		if(StringUtils.hasText(msg.note))
			msg.note=msgStatus+"\n"+msg.note;
		else
			msg.note=msgStatus;
		Message msgToSave=this.createMessage(msg,order);
		messageRepository.save(msgToSave);
		order.setStatus(status);
		if(status==OrdinationStatusEnum.ENDED) {
			TablesDTO t=new TablesDTO();
			t.isFree=true;
			tablesService.update(t);
		}
		return order;
	}

	private Message createMessage(MessageDTO msg,Ordination o) throws Exception{
		if(o==null)
			o=ordinationRepository.findByUuid(msg.ordinationUuid);
		if(msg==null || !StringUtils.hasText(msg.note) || !StringUtils.hasText(msg.userUuid))
				throw new Exception("note and user is required for the message");
		else {
			UserAccount u= userAccountRepository.findByUuid(msg.userUuid);
			if(u==null) throw new Exception("User with uuid: "+msg.userUuid+" not found");
			return new Message(msg.note,u,o);
		}
	}
	
	@Override
	public Ordination get(String uuid) throws Exception {
		Ordination o=this.ordinationRepository.findByUuid(uuid);
		if(o==null) throw new Exception("Order with uuid "+uuid+" not exists");
		return o;
	}
	
	private void buildOrdinationByDTO(Ordination ordination, OrdinationDTO ordinationDTO) throws Exception {
		if(ordinationDTO.status != null) 
			ordination.setStatus(ordinationDTO.status);
		if(ordinationDTO.dateCreation == null) 
			ordination.setDateCreation(new Date());
		else
			ordination.setDateCreation(ordinationDTO.dateCreation);
		ordination.setDateLastModified(new Date());
		if(StringUtils.hasText(ordinationDTO.tableUuid)) {
			Tables t=tablesService.get(ordinationDTO.tableUuid);
			if(t==null)throw new Exception("Table with uuid: "+ordinationDTO.tableUuid+" not found");
			ordination.setTable(t);
		}
		if(!StringUtils.hasText(ordinationDTO.uuid))
			ordination.setUuid(UUID.randomUUID().toString());
		else
			ordination.setUuid(ordinationDTO.uuid);
		if(ordinationDTO.total!=null)
			ordination.setTotal(ordinationDTO.total);
		else 
			ordination.setTotal(0.0);
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
	public Ordination addCocktail(OrderedCocktailDTO ord) throws Exception {
		OrderedCocktail ocSearch = orderedCocktailRepository.findByOrderUuidAndCocktailUuid(ord.ordinationUuid, ord.cocktailUuid);
		OrderedCocktail ocRes;
		if(ocSearch==null) {
			Ordination o=this.get(ord.ordinationUuid);
			Cocktail c=cocktailService.get(ord.cocktailUuid);
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
		this.useProductForCocktail(ord.cocktailUuid, CocktailServiceI.DECREMENT);
		Integer actualNumber = ocRes.getOrdination().getNumbersOfCocktails();
		ocRes.getOrdination().setNumbersOfCocktails(actualNumber + 1);
		Double actualTotal = ocRes.getOrdination().getTotal();
		ocRes.getOrdination().setTotal(actualTotal + ocRes.getCocktail().getPrice());
		cocktailService.updateSold(ocRes.getCocktail().getUuid(), CocktailService.INCREMENT);
		return ocRes.getOrdination();
	}
	
	private void useProductForCocktail(String cocoktailUuid,int operation) throws Exception{
		System.out.println("@@@@@@ cocoktailUuid "+cocoktailUuid+" operation: "+operation);
		Set<Ingredient> ingredients = cocktailService.getIngredients(cocoktailUuid);
		Iterator<Ingredient> i = ingredients.iterator();	
		while(i.hasNext()) {
			   Ingredient ingredient=i.next();
			   if(ingredient.getProduct().getMl()!=null) {
				   
				   this.updateProductQuantity(ingredient.getProduct(), ingredient.getQuantity()*operation);
				   ingredientRepository.deactivateFinischedIngredients(ingredient.getProduct().getUuid());
			   }
			}
	}
	
	private void updateProductQuantity(Product p,int quantity) throws Exception {
		System.out.println("@@@@@@ product "+p.getName()+" quatity: "+quantity);
		if(p.getMl()+quantity>=0) {
			p.setMl(p.getMl()+quantity);
			if(p.getMl()==0)p.setPresent(false);
		}else throw new Exception("quantity not sufficient for product "+p.getCategory()+" "+p.getName());
	}

	
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

	@Override
	public Ordination removeCocktail(OrderedCocktailDTO ord) throws Exception {
		OrderedCocktail ocSearch = orderedCocktailRepository.findByOrderUuidAndCocktailUuid(ord.ordinationUuid, ord.cocktailUuid);
		if(ocSearch==null)
			throw new Exception("Ordination or Cocktail not exist");
			
		Ordination ordination = ocSearch.getOrdination();
		if(ordination.getStatus() == OrdinationStatusEnum.CREATED || ordination.getStatus() == OrdinationStatusEnum.PENDING){
		this.useProductForCocktail(ord.cocktailUuid,CocktailServiceI.INCREMENT);
		Integer actualNumber = ocSearch.getOrdination().getNumbersOfCocktails();
		ocSearch.getOrdination().setNumbersOfCocktails(actualNumber - 1);
		Double actualTotal = ocSearch.getOrdination().getTotal();
		ocSearch.getOrdination().setTotal(actualTotal - ocSearch.getCocktail().getPrice());
		cocktailService.updateSold(ocSearch.getCocktail().getUuid(), CocktailService.DECREMENT);
		if(ocSearch.getQuantity() != 1) 
			ocSearch.setQuantity(ocSearch.getQuantity()-1);
		else
			orderedCocktailRepository.delete(ocSearch);
		}
		
		return ordination;
	}
	
	@Override
	public Ordination update(String uuidOrder,String uuidTable) throws Exception {
		Ordination o = this.get(uuidOrder);
		Tables t= tablesService.get(uuidTable);
		o.setTable(t);
		return o;
	}
	
	public List<Ordination> findOpenOrdinationForTableUuid(String tableUuid){
		return ordinationRepository.findOpenOrdinationForTableUuid(OrdinationStatusEnum.ENDED, tableUuid);
	}
}
