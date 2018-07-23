package com.rest.springboot.employee.controllers;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest.springboot.employee.models.EmployeeModel;
import com.rest.springboot.employee.repositories.IEmployeeRepository;
import com.rest.springboot.employee.repositories.IEmployeeServiceRepository;
import com.rest.springboot.exceptions.ServiceResourceNotFoundException;
import com.rest.springboot.utils.ServiceUtil;

@RestController
@RequestMapping("/api")
public class EmployeeServiceController 
{	
	private static final org.apache.log4j.Logger log = LogManager.getLogger(EmployeeServiceController.class); 
	
	@Autowired
	IEmployeeRepository iEmployeeRepository;
	
	@Autowired
	IEmployeeServiceRepository iEmployeeServiceRepository;
	
	@GetMapping("/employee/uuid/{uuid}")
	public Optional<EmployeeModel> getEmployeeById(@PathVariable(value = "uuid") final String uuid) 
	{		
		ServiceUtil.isNotNull(uuid);
		
		Optional<EmployeeModel> eM = iEmployeeRepository.findByUuid(uuid);		
		
		if ( eM.orElse(null) != null && eM.isPresent())
		{
			return eM;
		}	
		else
		{
			log.error("Resource not found");
			
			throw new ServiceResourceNotFoundException("Employee", "uuid", uuid);	
		}				
	}
	
	@GetMapping("/employee")
	public List<EmployeeModel> getEmployeeList() 
	{		
			return iEmployeeRepository.findEmployeeList();		
	}
	
	@PostMapping("/employee")
	@CacheEvict(cacheNames="employeeEmailList", allEntries=true)
	public @Valid Optional<EmployeeModel> createEmployee(@Valid @RequestBody EmployeeModel employeeModel) 
	{							     
		ServiceUtil.isNotNull(employeeModel);
		
		return iEmployeeServiceRepository.addEmployee(employeeModel);
		//OR
		//return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/employee/uuid/{uuid}")
	@CacheEvict(cacheNames="employeeEmailList", allEntries=true)
	public ResponseEntity<?> deleteEmployeeByUuid(@PathVariable(value = "uuid") final String uuid) 
	{		
		ServiceUtil.isNotNullAndNotEmpty(uuid);
		
		return iEmployeeServiceRepository.removeEmployee(uuid);
	}

	@PutMapping("/employee/uuid/{uuid}")
	@CacheEvict(cacheNames="employeeEmailList", allEntries=true)
	public Optional<EmployeeModel> updateEmployeeByUuid(@PathVariable(value = "uuid") final String uuid, 
			@Valid @RequestBody EmployeeModel employeeModel ) 
	{		
		ServiceUtil.isNotNull(uuid);
		ServiceUtil.isNotNull(employeeModel);
		
		return iEmployeeServiceRepository.updateEmployee(uuid, employeeModel);				
	}
}
