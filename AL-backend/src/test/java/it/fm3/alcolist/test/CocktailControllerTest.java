package it.fm3.alcolist.test;

import java.util.ArrayList;
import java.util.List;

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

import it.fm3.alcolist.DTO.CocktailDTO;
import it.fm3.alcolist.DTO.CocktailResultDTO;
import it.fm3.alcolist.controller.CocktailController;
import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.service.CocktailServiceI;

@WebMvcTest(CocktailController.class)
class CocktailControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CocktailServiceI cocktailService;

	private List<Cocktail> cocktails;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		cocktails = new ArrayList<Cocktail>();
	}
	
	@Test
	@DisplayName("Search Cocktail By Name")
	void testSearchCocktailByName() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
//		Cocktail c = new Cocktail();
//		c.setName("Negroni");
//		c.setFlavour("Amaro");
//		c.setDescription("Descrizione Negroni");
//		c.setAlcoholic(true);
//		c.setIBA(true);
//		c.setInMenu(true);
//		c.setPrice(5.5);
//		c.setSold(4);
//		cocktails.add(c);
//		c.setName("Americano");
//		c.setFlavour("Amaro");
//		c.setDescription("Descrizione Americano");
//		c.setAlcoholic(true);
//		c.setIBA(true);
//		c.setInMenu(true);
//		c.setPrice(6.0);
//		c.setSold(3);
//		cocktails.add(c);
//		c.setName("Calice di Prosecco");
//		c.setFlavour("Secco");
//		c.setDescription("\"Moët Impérial è lo champagne iconico della Maison. Creato nel 1869, incarna lo stile unico di Moët & Chandon, uno stile che si distingue per il suo fruttato brillante, il suo palato seducente e la sua elegante maturità.\"");
//		c.setAlcoholic(true);
//		c.setIBA(false);
//		c.setInMenu(true);
//		c.setPrice(3.0);
//		c.setSold(0);
//		cocktails.add(c);
		
		CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,0,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", null, null);
		
		Mockito.when(cocktailService.searchByFields(cocktailDTO))
		.thenReturn(cocktailResultDTO);
		
		//definisco cio che passo in chiamata api mcvresult
		//objectMapper.writeValueAsString(cocktailDTO);
		String json =  "{" +
				"\"name\": \"Negroni\"," +  
				"\"flavour\": \"\"," + 
				"\"isAlcoholic\": [\"\"]}";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				//.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		System.out.println("SUPEROOOOOOOO ");
		String responseAsString = mvcResult.getResponse().getContentAsString();
		System.out.println("SUPEROOOOOOOOOOOO ");
		System.out.println("Tutto bene! :-)" + responseAsString);	
	}
}