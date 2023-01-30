package it.fm3.alcolist.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import it.fm3.alcolist.DTO.UserAccountDTO;
import it.fm3.alcolist.entity.Role;
import it.fm3.alcolist.entity.UserAccount;
import it.fm3.alcolist.repository.RoleRepository;
import it.fm3.alcolist.repository.UserAccountRepository;
import it.fm3.alcolist.service.EmailServiceI;
import it.fm3.alcolist.service.UserAccountService;



@WebMvcTest(UserAccountService.class)
class UserAccountServiceTest {

	/*
	@Autowired
	private MockMvc mockMvc;
	*/
	//@InjectMocks
	@Autowired
    private UserAccountService userAccountService;
	
	@MockBean
	private UserAccountRepository userAccountRepository;
	
	@MockBean
	private RoleRepository roleRepository;
	
	@MockBean
	private EmailServiceI emailService;

    @BeforeEach
    public void setup(){
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
    	//emailService= new EmailService();
    	roleRepository=Mockito.mock(RoleRepository.class);
    	//userAccountRepository=Mockito.mock(UserAccountRepository.class);
    	
    }
	
	@Test
	@DisplayName("Add User")
	void testSaveUser() throws Exception {
		//definsco ciò che passo all'add
		ArrayList<String> rolesListDto = new ArrayList<String>();
		rolesListDto.add("WAITER");
		rolesListDto.add("MANAGER");
		UserAccountDTO userDTO = new UserAccountDTO("Prova", "Testing", rolesListDto, "Co-proprietario" , "mailprova@test.it");
		
		// given - precondition or setup
		BDDMockito.given(userAccountRepository.findByEmailAndDelateDateIsNull(userDTO.email)).willReturn(null);
		Role x  =new Role();
		x.setName("WAITER");
		BDDMockito.given(roleRepository.findByName(any(String.class))).willReturn(x);
		UserAccount savedEmployee = userAccountService.add(userDTO);
	
		assertThat(savedEmployee).isNotNull();
	}
	/*
		
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
	*/
}