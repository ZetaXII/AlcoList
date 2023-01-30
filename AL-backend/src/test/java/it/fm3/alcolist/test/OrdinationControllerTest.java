package it.fm3.alcolist.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.fm3.alcolist.DTO.OrderedCocktailDTO;
import it.fm3.alcolist.DTO.OrdinationDTO;
import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.entity.OrderedCocktail;
import it.fm3.alcolist.entity.Ordination;
import it.fm3.alcolist.entity.Role;
import it.fm3.alcolist.entity.Tables;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.service.OrdinationServiceI;
import it.fm3.alcolist.utils.OrdinationStatusEnum;

@WebMvcTest(OrdinationControllerTest.class)
public class OrdinationControllerTest {
	
	/*************************************
	 * TEST CONTROLLER PER L'INVIO COMANDA
	*************************************/
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrdinationServiceI ordinationService;

	private Ordination ordination;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	void setup() {
		ordination = new Ordination();
	}
	
	@Test
	@DisplayName("Invio Comanda")
	void testSentCommand() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		/* 
		 * mi dichiaro un utente che effettua l'ordinazione
		*/
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		UserAccount user = new UserAccount("Prova", "testing",rolesList, "WAITER", "mailprova@test.it");
		/* 
		 * setto l'ordinazione
		*/
		ordination.setUuid("eewfwe-efwffwf-2232sa-fdsfsdf");
		ordination.setCreatedBy(user);
		ordination.setTable(new Tables(1, true, "U2uu3-hsdbjhde-ksadadasd-dak2223"));
		ordination.setDateCreation(new Date());
		ordination.setNumbersOfCocktails(1);
		Set<OrderedCocktail> ordsCocktails = new HashSet<OrderedCocktail>();
		Cocktail cocktail = new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4);
		OrderedCocktail ordCocktail = new OrderedCocktail();
		ordCocktail.setCocktail(cocktail);
		ordCocktail.setOrdination(ordination);
	    ordCocktail.setQuantity(1);
		ordsCocktails.add(ordCocktail);
		ordination.setOrderedCocktails(ordsCocktails);
		ordination.setStatus(OrdinationStatusEnum.CREATED);
		
		
		//definsco ciò che passo alla created
		// String cocktailUUID = "cock2-uuido22-peropriv-sjsj";
		// ArrayList<String> cocktailsUuidList = new ArrayList<>();
		// cocktailsUuidList.add(cocktailUUID);
		// OrdinationDTO ordinationDTO = new OrdinationDTO(cocktailsUuidList, "U2uu3-hsdbjhde-ksadadasd-dak2223", OrdinationStatusEnum.CREATED, new Date(), new Date(), "uuid-perordinati-snsn-2383", 5.5, user.getUuid());
		OrdinationDTO ordinationDTO = new OrdinationDTO("U2uu3-hsdbjhde-ksadadasd-dak2223", user.getUuid());
		ordinationDTO.uuid = "eewfwe-efwffwf-2232sa-fdsfsdf";
		OrderedCocktailDTO orderedCocktailDTO = new OrderedCocktailDTO(cocktail.getUuid(), "eewfwe-efwffwf-2232sa-fdsfsdf"); 
		
		Mockito.when(ordinationService.addCocktail(Mockito.any(OrderedCocktailDTO.class)))
		.thenReturn(ordination);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(orderedCocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-ordinations/addCocktail")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				//.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
		
		System.out.println("STA?M" + responseAsString);
		
		//Assertions.assertEquals(bodyRequest, bodyResponse);
		System.out.println("Tutto bene! :-)");	
	}
}
