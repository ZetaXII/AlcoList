package it.fm3.alcolist.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.entity.Role;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.repository.CocktailRepository;
import it.fm3.alcolist.repository.OrdinationRepository;
import it.fm3.alcolist.repository.UserAccountRepository;

@Service
@Transactional
public class StatisticsService implements StatisticsServiceI{
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private CocktailRepository cocktailRepository;
	@Autowired
	private OrdinationRepository ordinationRepository;
	
	@Override
	public Integer getByNumbersOfUsers(String role) {
		
		List<UserAccount> usersList = userAccountRepository.findAll();
		List<UserAccount> usersFilteredRoleList = new ArrayList<>();;
		
		for(UserAccount u : usersList) {
			List<Role> uRoles = u.getRoles();
			int numbersRoles = uRoles.size();
			if(role.equalsIgnoreCase("WAITER")) {
				for(Role r : uRoles) {
					if(r.getName().equalsIgnoreCase(role) && numbersRoles == 1) {
						usersFilteredRoleList.add(u);
					}
				}
			}else if(role.equalsIgnoreCase("BARTENDER")) {
				boolean bart = false;
				boolean man = false;
				for(Role r : uRoles) {
					if(r.getName().equalsIgnoreCase(role)){
						bart = true;
					}
					if(r.getName().equalsIgnoreCase("MANAGER")){
						man = true;
					}
				}
				if(bart && !man) {
					usersFilteredRoleList.add(u);
				}
			}
			else if(role.equalsIgnoreCase("MANAGER")) {
				for(Role r : uRoles) {
					if(r.getName().equalsIgnoreCase(role)) {
						usersFilteredRoleList.add(u);
					}
				}
			}	
		}
		return usersFilteredRoleList.size();
	}
	
	@Override
	public Integer getNumbersOfCreatedByUser(String UserUuid) throws Exception {
		return ordinationRepository.countByCreatedBy(UserUuid);
	}

	@Override
	public Integer getNumbersOfDeliveredByUser(String UserUuid) throws Exception {
		return ordinationRepository.countByDeliveredBy(UserUuid);
	}

	@Override
	public Integer getNumbersOfExecutedByUser(String UserUuid) throws Exception {
		return ordinationRepository.countByExecutedBy(UserUuid);
	}

	@Override
	public List<Cocktail> getBestSellingCocktails(Integer limit) throws Exception {
			if(limit == null) 
				throw new Exception("Limit undefined");
			Pageable p = null;
			p = PageRequest.of(0, limit);
			return cocktailRepository.findAllByOrderBySoldDesc(p);
			//return cocktailRepository.findTop5ByOrderBySoldDesc();
	}

	@Override
	public List<Cocktail> getBestSellingCocktailsByFlavour() throws Exception {
		List<Integer> totalSold = cocktailRepository.findBestSellingByFlavour();
		
		for(Integer i : totalSold) {
			if(i == null)
				throw new Exception("Error filtered flavour sold");
		}
		
		List<Cocktail> list = new ArrayList<Cocktail>();
		Cocktail a = new Cocktail();
		a.setFlavour("Amaro");
		a.setSold(totalSold.get(0));
		list.add(0, a);
		
		Cocktail c = new Cocktail();
		c.setFlavour("Aspro");
		c.setSold(totalSold.get(1));
		list.add(1, c);
		
		Cocktail d = new Cocktail();
		d.setFlavour("Dolce");
		d.setSold(totalSold.get(2));
		list.add(2, d);
		
		Cocktail s = new Cocktail();
		s.setFlavour("Secco");
		s.setSold(totalSold.get(3));
		list.add(3, s);
		
		return  list;
	}

}
