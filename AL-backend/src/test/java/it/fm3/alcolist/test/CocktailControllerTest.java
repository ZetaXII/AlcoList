package it.fm3.alcolist.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import it.fm3.alcolist.DTO.CocktailDTO;
import it.fm3.alcolist.DTO.CocktailResultDTO;
import it.fm3.alcolist.controller.CocktailController;
import it.fm3.alcolist.entity.Cocktail;
import it.fm3.alcolist.service.CocktailServiceI;

@WebMvcTest(CocktailController.class)
class CocktailControllerTest {
	
	/*****************************************
	 * TEST CONTROLLER PER RICERCA COCKTAIL
	*****************************************/

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CocktailServiceI cocktailService;

	private List<Cocktail> cocktails;
	
	Cocktail cocktail;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		cocktails = new ArrayList<Cocktail>();
	}
	
	/**********
	* TC_2.1_04
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Nome")
	void testSearchCocktailByName() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		//cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		//cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", "", null, 0, 5);
		
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenReturn(cocktailResultDTO);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
		System.out.println("Tutto bene! :-)" + responseAsString);	
	}
	
	/**********
	* TC_2.1_01
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Nome Alfanumerico")
	void testSearchCocktailByNameBadExceptionNameOnlyCarachters() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		//CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("123", "", null, 0, 5);
		
		String messageError = "Nome deve essere una stringa alfanumerica";
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");	
	}
	
	/**********
	* TC_2.1_02
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Nome Troppo Corto")
	void testSearchCocktailByNameBadExceptionNameShort() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		//CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("GI", "", null, 0, 5);
		
		String messageError = "Nome deve essere una stringa di almeno 3 caratteri";
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");	
	}
	
	/**********
	* TC_2.1_03
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Nome Troppo Lungo")
	void testSearchCocktailByNameBadExceptionNameLong() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		//CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("GInjsnjnajnfkbjwkfwewfwebfkwbefhbewjfbwhjfbebfhjwfbwj73", "", null, 0, 5);
		
		String messageError = "Nome deve essere una stringa di massimo 30 caratteri";
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");	
	}
	
	/**********
	* TC_2.2_01
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Gusto")
	void testSearchCocktailByFlavour() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		//cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		//cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("", "Dolce", null, 0, 5);
		
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenReturn(cocktailResultDTO);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
		System.out.println("Tutto bene! :-)" + responseAsString);	
	}
	
	/**********
	* TC_2.3_01
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Alcol")
	void testSearchCocktailByAlcoholic() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("", "", true, 0, 5);
		
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenReturn(cocktailResultDTO);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
		System.out.println("Tutto bene! :-)" + responseAsString);	
	}
	
	/**********
	* TC_2.4_01
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Completa con Nome Alfanumerico")
	void testSearchCocktailByAllBadExceptionNameOnlyCarachters() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		//CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("123", "", null, 0, 5);
		
		String messageError = "Nome deve essere una stringa alfanumerica";
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");	
	}
	
	/**********
	* TC_2.4_02
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Completa con Nome Troppo Corto")
	void testSearchCocktailAllBadExceptionNameShort() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		//CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("GI", "", null, 0, 5);
		
		String messageError = "Nome deve essere una stringa di almeno 3 caratteri";
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");	
	}
	
	/**********
	* TC_2.4_03
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Completa con Nome Troppo Lungo")
	void testSearchCocktailAllBadExceptionNameLong() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		//CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("GInjsnjnajnfkbjwkfwewfwebfkwbefhbewjfbwhjfbebfhjwfbwj73", "", null, 0, 5);
		
		String messageError = "Nome deve essere una stringa di massimo 30 caratteri";
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");	
	}
	
	/**********
	* TC_2.4_04
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Nome Gusto")
	void testSearchCocktailByNameFlavour() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		//cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		//cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", "Amaro", null, 0, 5);
		
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenReturn(cocktailResultDTO);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
		System.out.println("Tutto bene! :-)" + responseAsString);	
	}
	
	/**********
	* TC_2.4_05
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Nome Alcol")
	void testSearchCocktailByNameAlcoholic() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		//cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		//cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", "", true, 0, 5);
		
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenReturn(cocktailResultDTO);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
		System.out.println("Tutto bene! :-)" + responseAsString);	
	}
	
	/**********
	* TC_2.4_06
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Gusto Alcol")
	void testSearchCocktailByFlavourAlcoholic() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		//cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		//cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("", "Amaro", true, 0, 5);
		
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenReturn(cocktailResultDTO);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
		System.out.println("Tutto bene! :-)" + responseAsString);	
	}
	
	/**********
	* TC_2.4_07
	**********/
	@Test
	@DisplayName("Ricerca Cocktail Completa")
	void testSearchCocktailByAll() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktails.add(new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 4));
		//cocktails.add(new Cocktail("Americano", 6.0, "Descrizione Americano", "Dolce", true, true, true, 3));
		//cocktails.add(new Cocktail("Calice di Prosecco", 4.0, "Descrizione Prosecco", "Secco", false, true, true, 1));
		
		CocktailResultDTO cocktailResultDTO= new CocktailResultDTO(1,1,1,cocktails);
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", "Amaro", true, 0, 5);
		
		Mockito.when(cocktailService.searchByFields(Mockito.any(CocktailDTO.class)))
		.thenReturn(cocktailResultDTO);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/searchByFields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
		System.out.println("Tutto bene! :-)" + responseAsString);	
	}

	
	
	/***************************************
	 * TEST CONTROLLER PER AGGIUNTA COCKTAIL
	***************************************/
	
	/********
	* TC_3_01 Bisogna inserire il Nome del cocktail
	********/
	@Test
	@DisplayName("AddCocktailNomeVuoto")
	void testAddCocktailBadExceptionVoidName() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		//cocktail = new Cocktail("Neg", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 0, "pathimg");
				
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 5, "pathimg");
		//cocktailDTO.uuid = cocktail.getUuid();
				
		String messageError = "Bisogna inserire il Nome del cocktail";
		Mockito.when(cocktailService.add(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
	/********
	* TC_3_02 Il nome deve essere una stringa di almeno 3 caratteri
	********/
	@Test
	@DisplayName("AddCocktailNomeCorto")
	void testAddCocktailBadExceptionShortName() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		//cocktail = new Cocktail("Neg", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 0, "pathimg");
				
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Ne", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 5, "pathimg");
		//cocktailDTO.uuid = cocktail.getUuid();
				
		String messageError = "Il Nome deve essere una stringa di almeno 3 caratteri";
		Mockito.when(cocktailService.add(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content(json).json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
	/********
	* TC_3_03 Il nome deve essere una stringa di massimo 30 caratteri
	********/
	@Test
	@DisplayName("AddCocktailNomeLungo")
	void testAddCocktailBadExceptionLongName() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		//cocktail = new Cocktail("Neg", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 0, "pathimg");
				
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negronifintroppolungoprobabilmentepiuditrentacaratteri", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 5, "pathimg");
		//cocktailDTO.uuid = cocktail.getUuid();
				
		String messageError = "Il Nome deve essere una stringa di massimo 30 caratteri";
		Mockito.when(cocktailService.add(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content(json).json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
	/********
	* TC_3_04 La descrizione non può essere vuota
	********/
	@Test
	@DisplayName("AddCocktailDescrizioneVuota")
	void testAddCocktailBadExceptionVoidDescription() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		//cocktail = new Cocktail("Neg", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 0, "pathimg");
				
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", 5.5, "", "Amaro", true, true, true, 5, "pathimg");
		//cocktailDTO.uuid = cocktail.getUuid();
				
		String messageError = "La descrizione non può essere vuota";
		Mockito.when(cocktailService.add(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content(json).json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
	/********
	* TC_3_05 Il path dell’immagine non può essere vuoto
	********/
	@Test
	@DisplayName("AddCocktailPathImgVuota")
	void testAddCocktailBadExceptionVoidPathFileImg() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		//cocktail = new Cocktail("Neg", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 0, "pathimg");
				
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 5, "");
		//cocktailDTO.uuid = cocktail.getUuid();
				
		String messageError = "Il path dell’immagine non può essere vuoto";
		Mockito.when(cocktailService.add(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content(json).json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
	/********
	* TC_3_06 Tutti i campi devono essere compilati
	********/
	@Test
	@DisplayName("AddCocktailPathImgVuota")
	void testAddCocktailBadExceptionVoidPrice() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		//cocktail = new Cocktail("Neg", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 0, "pathimg");
				
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", 0.0, "", "Amaro", true, true, true, 5, "");
		//cocktailDTO.uuid = cocktail.getUuid();
				
		String messageError = " Tutti i campi devono essere compilati";
		Mockito.when(cocktailService.add(Mockito.any(CocktailDTO.class)))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content(json).json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
	/********
	* Eviteremo di farlo in quanto identico a TC_3_08 
	* TC_3_07 Il Sistema mostra un messaggio di avvenuta creazione e reindirizza il Manager alla schermata di aggiunta degli ingredienti
	********/
	/********
	* TC_3_08 Il Sistema visualizza un messaggio di avvenuta aggiunta ingredienti e reindirizza l’utente alla pagina di lista cocktail.
	********/
	@DisplayName("Aggiunta Cocktail D'Autore")
	@Test
	void testAddCocktail() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		cocktail = new Cocktail("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 0, "pathimg");
		
		//definsco ciò che passo all'add
		CocktailDTO cocktailDTO = new CocktailDTO("Negroni", 5.5, "Descrizione Negroni", "Amaro", true, true, true, 5, "pathimg");
		cocktailDTO.uuid = cocktail.getUuid();
		
		Mockito.when(cocktailService.add(Mockito.any(CocktailDTO.class)))
		.thenReturn(cocktail);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(cocktailDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-cocktails/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
	
		String nameRequest = JsonPath.parse(json).read("$.name");
		//Double priceRequest = JsonPath.parse(json).read("$.price");
		String descriptionRequest = JsonPath.parse(json).read("$.description");
		String flavourRequest = JsonPath.parse(json).read("$.flavour");
		String pathFileImgRequest = JsonPath.parse(json).read("$.pathFileImg");
		String uuidRequest = JsonPath.parse(json).read("$.uuid");
		//String inMenuRequest = JsonPath.parse(json).read("$.inMenu");
		//String ibaRequest = JsonPath.parse(json).read("$.iba");
		String bodyRequest = nameRequest + descriptionRequest + flavourRequest + pathFileImgRequest + uuidRequest;
		
		String nameResponse = JsonPath.parse(responseAsString).read("$.name");
		//Double priceResponse = JsonPath.parse(responseAsString).read("$.price");
		String descriptionResponse = JsonPath.parse(responseAsString).read("$.description");
		String flavourResponse = JsonPath.parse(responseAsString).read("$.flavour");
		String pathFileImgResponse = JsonPath.parse(responseAsString).read("$.pathFileImg");
		String uuidResponse = JsonPath.parse(responseAsString).read("$.uuid");
		//String inMenuResponse = JsonPath.parse(responseAsString).read("$.inMenu");
		//String ibaResponse = JsonPath.parse(responseAsString).read("$.isIba");
		String bodyResponse = nameResponse + descriptionResponse + flavourResponse + pathFileImgResponse + uuidResponse;
		
		Assertions.assertEquals(bodyRequest, bodyResponse);
		System.out.println("Tutto bene! :-)");	
	}
	/********
	* Eviteremo di farlo in quanto identico a TC_3_08 
	* TC_3_09 Il Sistema visualizza un messaggio di avvenuta aggiunta ingredienti e reindirizza l’utente alla pagina di lista cocktail.
	********/
	
	
}