package it.fm3.alcolist.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

	@BeforeEach
	void setup() {
		user = new UserAccount();
		user.setName("Prova");
		user.setSurname("Testing");
		user.setEmail("mailprova@test.it");
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
	}
	
	@Test
	void testSaveUser() throws Exception {
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		Mockito.when(userService.add(userDTO)).thenReturn(user);
		
//		String c = "{\r\n" + 
//				"  \"name\": \"Prova\",\r\n" +  
//				"  \"surname\": \"Testing\",\r\n" + 
//				"  \"roleList\": [\"WAITER\"] ,\r\n" + 
//				"  \"mainRole\": \"WAITER\",\r\n" + 
//				"  \"email\": \"mailprova@test.it\"\r\n" + 
//				"}";
		
		String json = "{" +
				"\"name\": \"Prova\"," +  
				"\"surname\": \"Testing\"," + 
				"\"roleList\": [\"WAITER\"]," + 
				"\"mainRole\": \"WAITER\"," + 
				"\"email\": \"mailprova@test.it\"}";		
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
	void testSaveUserBadException() throws Exception {
		//user = new UserAccount();
//		user.setName("P");
//		user.setSurname("Testing");
//		user.setEmail("mailprova@test.it");
		List<Role> rolesList = new ArrayList<>();
		Role waiter = new Role();
		waiter.setName("WAITER");
		rolesList.add(waiter);
		user.setMainRole(rolesList.get(0).getName());
		user.setRoles(rolesList);
		ArrayList<String> rolesListDto = new ArrayList<String>();
		String waiterDto = "WAITER";
		rolesListDto.add(waiterDto);
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, waiterDto , "mailprova@test.it");
		
		UserAccount error = null;
		Mockito.when(userService.add(userDTO)).thenReturn(error);
		
//		String c = "{\r\n" + 
//				"  \"name\": \"Prova\",\r\n" +  
//				"  \"surname\": \"Testing\",\r\n" + 
//				"  \"roleList\": [\"WAITER\"] ,\r\n" + 
//				"  \"mainRole\": \"WAITER\",\r\n" + 
//				"  \"email\": \"mailprova@test.it\"\r\n" + 
//				"}";
		
		String json = "{" +
				"\"name\": \"P\"," +  
				"\"surname\": \"Testing\"," + 
				"\"roleList\": [\"WAITER\"]," + 
				"\"mainRole\": \"WAITER\"," + 
				"\"email\": \"mailprova@test.it\"}";		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(MockMvcResultHandlers.print())
				//.andExpect(content().json())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()
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
	
	
//	@Test
//	void testGetUser() throws Exception {
//		
//		user = new UserAccount();
//		user.setUuid("jnwdiqund-akjh323323-dwadsd-sadasda");
//		user.setName("FEDE");
//		user.setSurname("DIZ");
//		user.setEmail("fede@gmail.com");
//		List<Role> rolesList = new ArrayList<>();
//		user.setMainRole("MANAGER");
//		user.setRoles(rolesList);
//		user.setPassword("2buhdabwhbfwh");
//		
//		String jsonExspected = "{\"uuid\":\"jnwdiqund-akjh323323-dwadsd-sadasda\","
//				+ ""
//				+ "}";
//		
//		UserAccountDTO userDTO = new UserAccountDTO("jnwdiqund-akjh323323-dwadsd-sadasda");
//		
//		Mockito.when(userService.get(userDTO.uuid)).thenReturn(user);
//				
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/manage-users/get/jnwdiqund-akjh323323-dwadsd-sadasda")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(""))
//				.andDo(MockMvcResultHandlers.print())
//				//.andExpect(content().json())
//				.andExpect(MockMvcResultMatchers.status().isOk()
//				).andReturn();
//		
//		String resultAsString = mvcResult.getResponse().getContentAsString();
//		
//		System.out.println(resultAsString);
//		Assertions.assertEquals(bodyRequest, bodyResponse);
//		System.out.println("Tutto bene! :-)");	
//	}
		
		//ObjectMapper objectMapper = new ObjectMapper(); // com.fasterxml.jackson.databind.ObjectMapper
		//UserAccount myResponse = objectMapper.readValue(responseAsString, UserAccount.class);
		//System.out.println(myResponse);
		//MyResponse myResponse = objectMapper.readValue(responseAsString, MyResponse.class);
	
////	@Test
////	public void shouldReturnErrorMessageToAdminWhenCreatingUserWithUsedUserName() throws Exception {
////		String jsonErr= "{\"name\": \"Prova\",\"surname\": \"Testing\",\"roleList\": [\"WAITER\"],\"mainRole\": \"WAITER\",\"email\": \"fede@gmail.com\"}";
////		
////		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
////				//.header("Authorization")
////				.contentType(MediaType.APPLICATION_JSON)
////				.content(jsonErr))
////				//.andExpect(.content().json(jsonErr))
////				//.andDo(MockMvcResultHandlers.print())
////				.andExpect(MockMvcResultMatchers.status().isBadRequest())
////				.andReturn();
////		
////		String content = mvcResult.getResponse().getContentAsString();
////		System.out.println("OOOOO " + content);
////	}
//	
//	@Test
//	public void shouldReturnErrorMessageToAdminWhenCreatingUserWithUsedUserName() throws Exception {
//		String jsonErr= "{\"name\": \"Prova\",\"surname\": \"Testing\",\"roleList\": [\"WAITER\"],\"mainRole\": \"WAITER\",\"email\": \"fede@gmail.com\"}";
//		
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manage-users/add")
//				//.header("Authorization")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(jsonErr))
//				//.andExpect(.content().json(jsonErr))
//				//.andDo(MockMvcResultHandlers.print())
//				.andExpect(MockMvcResultMatchers.status().isBadRequest())
//				.andReturn();
//		
//		String content = mvcResult.getResponse().getContentAsString();
//		System.out.println("OOOOO " + content);
//	}

}