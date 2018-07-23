package com.rest.springboot.test.employee;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.google.gson.Gson;
import com.rest.springboot.employee.controllers.EmployeeServiceController;
import com.rest.springboot.employee.models.EmployeeModel;
import com.rest.springboot.employee.repositories.IEmployeeRepository;
import com.rest.springboot.employee.repositories.IEmployeeServiceRepository;
import com.rest.springboot.employee.service.EmployeeService;
import com.rest.springboot.utils.ServiceUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class EmployeeControllerTest 
{		     
	private MockMvc mockMvc;
	private EmployeeModel employeeModel;
	private String json = null;
	private Gson gson = null;
	private String uuid = null;

	@Autowired
	private IEmployeeRepository er;
	
	//Create mock object of controller and injects associated mocks.
	@InjectMocks
	private EmployeeServiceController employeeServiceController;
	
	@Before
	public void setUp()
	{	
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeServiceController).build();
		
		this.employeeModel = new EmployeeModel();
		
		Set<String> hobby = new HashSet<>();
		hobby.add("Cricket");
		hobby.add("Football");
		
		this.employeeModel.setEmail("ihoijojw@gmail.com");
		this.employeeModel.setEmployeeHobby(hobby);
		this.employeeModel.setFullName("Nilay");
		 
		uuid = ServiceUtil.getUuid();
		 
		this.employeeModel.setUuid(uuid);
		this.employeeModel.setBirthday(null);
		 
		this.gson = new Gson();
		this.json = gson.toJson(this.employeeModel);
	}
	
	@Test
	@Ignore
	public void testAddEmployee() throws Exception 
	{		
		mockMvc.perform(MockMvcRequestBuilders.post(("/api/employee"))
		.contentType(MediaType.APPLICATION_JSON).accept(json))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("ihoijojw@gmail.com")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.uuid", Matchers.is(this.uuid)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.fullName", Matchers.is("Nilay")));
	}

	@Test
	public void testGetEmployee() throws Exception 
	{		
		
		er.save(this.employeeModel);
	    
	    assertEquals("Name",(er.findById(this.uuid).get().getFullName()), "Nilay");
	    assertEquals("uuid",(er.findById(this.uuid).get().getUuid()), this.uuid);
	    assertEquals("Email",(er.findById(this.uuid).get().getEmail()), "ihoijojw@gmail.com");
	}
	
	
	@Test
	public void testUpdateEmployee() throws Exception 
	{			
		er.save(this.employeeModel);
		
		this.employeeModel.setFullName("Markanday");
		this.employeeModel.setEmail("nilay@yahoo.com");
		
		er.save(this.employeeModel);
	    
	    assertEquals("",(er.findById(this.uuid).get().getFullName()), "Markanday");
	    assertEquals("",(er.findById(this.uuid).get().getUuid()), this.uuid);
	    assertEquals("",(er.findById(this.uuid).get().getEmail()), "nilay@yahoo.com");
	}
	
	@Test
	public void testdeleteEmployee() throws Exception 
	{			
		er.save(this.employeeModel);
		
		er.deleteById(uuid);
		
		Optional<EmployeeModel> eM = er.findById(uuid);
	
		assertEquals(false, eM.isPresent());
	    
	}
}
