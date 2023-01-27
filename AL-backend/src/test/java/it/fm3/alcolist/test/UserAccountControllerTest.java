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
	@DisplayName("Add User")
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
	@DisplayName("NameMustBeAtLeastTwoCharacters")
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
		
		
		String messageError = "The length of the name must be at least 2 characters";
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
	@DisplayName("NameShouldBe30CharactersMaximum")
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
		
		
		String messageError = "The length of the name should be 30 characters maximum";
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