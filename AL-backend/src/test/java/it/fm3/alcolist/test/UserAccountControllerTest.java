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

import it.fm3.alcolist.DTO.UserAccountDTO;
import it.fm3.alcolist.controller.UserAccountController;
import it.fm3.alcolist.entity.Role;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.service.UserAccountServiceI;

@WebMvcTest(UserAccountController.class)
class UserAccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserAccountServiceI userService;

	private UserAccount user;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		user = new UserAccount();
	}
	
	@Test
	@DisplayName("Aggiunta Utente")
	void testSaveUser() throws Exception {
		//definisco ciò che mi aspetto nella response body (?)_
		user.setName("Prova");
		user.setSurname("Testing");
		user.setEmail("mailprova@test.it");
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		//definsco ciò che passo all'add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		Mockito.when(userService.add(userDTO))
		.thenReturn(user);
		
		//definisco cio che passo in chiamata api mcvresult
		String json = objectMapper.writeValueAsString(userDTO);
//		= "{" +
//				"\"name\": \"Prova\"," +  
//				"\"surname\": \"Testing\"," + 
//				"\"roleList\": [\"WAITER\"]," + 
//				"\"mainRole\": \"WAITER\"," + 
//				"\"email\": \"mailprova@test.it\"}";
		
		
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isOk()
				).andReturn();
		
		String responseAsString = mvcResult.getResponse().getContentAsString();
	
		String nameRequest = JsonPath.parse(json).read("$.name");
		String surnameRequest = JsonPath.parse(json).read("$.surname");
		String rolesRequest = JsonPath.parse(json).read("$.roleList.[0]");
		String mainRoleRequest = JsonPath.parse(json).read("$.mainRole");
		String emailRequest = JsonPath.parse(json).read("$.email");
		String bodyRequest = nameRequest + surnameRequest + rolesRequest + mainRoleRequest + emailRequest;
		
		String nameResponse = JsonPath.parse(responseAsString).read("$.name");
		String surnameResponse = JsonPath.parse(responseAsString).read("$.surname");
		String rolesResponse = JsonPath.parse(responseAsString).read("$.roles.[0].name");
		String mainRoleResponse = JsonPath.parse(responseAsString).read("$.mainRole");
		String emailResponse = JsonPath.parse(responseAsString).read("$.email");
		String bodyResponse = nameResponse + surnameResponse + rolesResponse + mainRoleResponse + emailResponse;
		
		
		Assertions.assertEquals(bodyRequest, bodyResponse);
		System.out.println("Tutto bene! :-)");	
	}
	
	@Test
	@DisplayName("NomeSoliCaratteri")
	void testSaveUserBadExceptionNameOnlyCharacters() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Pr0v4", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Nome deve essere una stringa di soli caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
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
	
	@Test
	@DisplayName("NomeAlmenoDueCaratteri")
	void testSaveUserBadExceptionNameMustBeAtLeastTwoCharacters() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("P", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Nome deve essere una stringa di almeno 2 caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
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
	
	@Test
	@DisplayName("CognomeSoliCaratteri")
	void testSaveUserBadExceptionSurnameOnlyCharacters() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Pr0v4", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Cognome deve essere una stringa di soli caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
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
	
	@Test
	@DisplayName("NomeMassimo30Caratteri")
	void testSaveUserBadExceptionNameShouldBe30CharactersMaximum() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Provaaggiuntaconunnomelunghissimochesuperaitrentacaratteri", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Nome deve essere una stringa di massimo 30 caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
		
	@Test
	@DisplayName("CognomeAlmenoDueCaratteri")
	void testSaveUserBadExceptionSurnameMustBeAtLeastTwoCharacters() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "T", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Cognome deve essere una stringa di almeno 2 caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
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
	
	@Test
	@DisplayName("CognomeMassimo30Caratteri")
	void testSaveUserBadExceptionSurnameShouldBe30CharactersMaximum() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Provaaggiuntaconuncognomenomelunghissimochesuperaitrentacaratteri", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Cognome deve essere una stringa di massimo 30 caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
	
	@Test
	@DisplayName("RuoloSoliCaratteri")
	void testSaveUserBadExceptionRoleOnlyCharacters() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WA1T3R");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Cognome deve essere una stringa di soli caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
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
	
	@Test
	@DisplayName("RuoloAlmeno5Caratteri")
	void testSaveUserBadExceptionRoleMustBeAtLeastFiveCharacters() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAIT");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAIT";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Ruolo deve essere una stringa di almeno 5 caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");

	}
	
	@Test
	@DisplayName("RuoloMassimo30Caratteri")
	void testSaveUserBadExceptionRoleShouldBe30CharactersMaximum() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITERRRRRRRRRRPROVACONUNASTRINGAMAGGIOREDI30CARATTERIIIIIIIII");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAIT";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		
		String messageError = "Il Ruolo deve essere una stringa di massimo 30 caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");
	}
	
	@Test
	@DisplayName("InserisciLEmail")
	void testSaveUserBadExceptionInsertEmail() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAIT";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "");
		
		
		String messageError = "Bisogna inserire l'email";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");
	}
	
	@Test
	@DisplayName("EmailAlmeno2Caratteri")
	void testSaveUserBadExceptionEmailMustBeAtLeastEightCharacters() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAIT";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "c@a.it");
		
		
		String messageError = "L’Email deve essere una stringa di almeno 8 caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");
	}
	
	@Test
	@DisplayName("EmailMassimo30Caratteri")
	void testSaveUserBadExceptionEmailShouldBe30CharactersMaximum() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAIT";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "c@a.it");
		
		
		String messageError = "L’Email deve essere una stringa di massimo 30 caratteri";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");
	}
	
	@Test
	@DisplayName("FomatoEmailNonValido")
	void testSaveUserBadExceptionValidFormatEmail() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAIT";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "mailgmail.it");
		
		
		String messageError = "L’Email deve avere un formato valido";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");
	}
	
	@Test
	@DisplayName("BisognaInserireIPermessi")
	void testSaveUserBadExceptionInsertPermission() throws Exception {
		//definisco ciò che mi aspetto (?)
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		
		
		//definisco cio che passo all/ add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAIT";
		//rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "mailgmail.it");
		
		
		String messageError = "Bisogna Inserire i Permessi";
		Mockito.when(userService.add(userDTO))
		.thenThrow(new Exception (messageError));
		
		String json = objectMapper.writeValueAsString(userDTO);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
				).andReturn();
		
		String bodyResponse = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals(messageError, bodyResponse);
		System.out.println("Tutto bene con l'Exception! :-)");
	}
}