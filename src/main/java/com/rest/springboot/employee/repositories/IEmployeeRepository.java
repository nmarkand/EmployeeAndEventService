package com.rest.springboot.employee.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springboot.employee.models.EmployeeModel;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeModel, String>{
	

	public static final String EMPLOYEE_TABLE = "t_employee";
	
	@Query(value = "SELECT EMAIL FROM "+ EMPLOYEE_TABLE, nativeQuery = true)
	@Cacheable("employeeEmailList")
	List<String> findEmployeeEmailList();
	
	@Query(value = "SELECT * FROM "+ EMPLOYEE_TABLE +  " WHERE is_deleted = 0", nativeQuery = true)
	List<EmployeeModel> findEmployeeList();
	
	@Query(value = "SELECT * FROM "+ EMPLOYEE_TABLE +  " WHERE uuid = ?1 AND is_deleted = 0", nativeQuery = true)
	Optional<EmployeeModel> findByUuid(final String uuid);

}
