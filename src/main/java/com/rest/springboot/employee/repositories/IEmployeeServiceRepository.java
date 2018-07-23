package com.rest.springboot.employee.repositories;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.rest.springboot.employee.models.EmployeeModel;

@Repository
public interface IEmployeeServiceRepository 
{
	public Optional<EmployeeModel> addEmployee(EmployeeModel employeeModel);
	
	public ResponseEntity<?> removeEmployee(final String employeeUuid);
	
	public Optional<EmployeeModel> updateEmployee(final String employeeUuid, 
			final EmployeeModel employeeModel);
	
}
