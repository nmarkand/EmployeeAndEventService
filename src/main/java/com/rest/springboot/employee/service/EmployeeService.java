package com.rest.springboot.employee.service;

import java.util.Date;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rabbitmq.RabbitMQProducer;
import com.rest.springboot.employee.models.EmployeeModel;
import com.rest.springboot.employee.repositories.IEmployeeRepository;
import com.rest.springboot.employee.repositories.IEmployeeServiceRepository;
import com.rest.springboot.exceptions.ServiceResourceNotCreatedOrUpdatedException;
import com.rest.springboot.exceptions.ServiceResourceNotFoundException;
import com.rest.springboot.utils.ServiceUtil;

@Service
public class EmployeeService implements IEmployeeServiceRepository
{
	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger( EmployeeService.class);
	
	private static final String EMPLOYEE_CREATED = "CREATE";
	private static final String EMPLOYEE_UPDATED = "UPDATE";
	private static final String EMPLOYEE_DELETED = "DELETE";
	
	@Autowired
	IEmployeeRepository iEmployeeRepository;
	
	@Autowired
	RabbitMQProducer rabbitMQProducer;
	
	@Autowired
	EmployeeModel employeeModel;
	
	public Optional<EmployeeModel> addEmployee(final EmployeeModel eM)
	{
		ServiceUtil.isNotNull(eM);
		
		if(ServiceUtil.isValidEmailAddress(eM.getEmail()) 
				&& !iEmployeeRepository.findEmployeeEmailList().contains(eM.getEmail()))
		{
			final String uuid = ServiceUtil.getUuid();
			
			employeeModel.setUuid(uuid);
			employeeModel.setBirthday(eM.getBirthday());
			employeeModel.setEmail(eM.getEmail());
			employeeModel.setEmployeeHobby(eM.getEmployeeHobby());
			employeeModel.setFullName(eM.getFullName());			
			employeeModel.setIsDeleted(false);
			
			try 
			{			
				iEmployeeRepository.save(employeeModel);
			} 
			catch (Exception e) 
			{	
				throw new ServiceResourceNotCreatedOrUpdatedException(eM.getFullName(), eM.getEmail());
			}	
			
			log.info("Employee persisted");
			
			rabbitMQProducer.sendEvent(employeeModel.getUuid(), EMPLOYEE_CREATED, ServiceUtil.getDateToString(new Date()));
			
			log.info("Employee created event " + uuid);
			
			return iEmployeeRepository.findById(uuid);
		}
		
		log.error("Invalid email.");
		
		throw new ServiceResourceNotCreatedOrUpdatedException(eM.getFullName(), eM.getEmail());
	}	
	
	
	public ResponseEntity<?> removeEmployee(final String employeeUuid)
	{
		ServiceUtil.isNotNullAndNotEmpty(employeeUuid);
		
		Boolean isEmployeeExist = iEmployeeRepository.existsById(employeeUuid);
	
		if (isEmployeeExist == true)
		{
			employeeModel = iEmployeeRepository.getOne(employeeUuid);
			
			if(!employeeModel.getIsDeleted())
			{
				employeeModel.setIsDeleted(true);
				
				try 
				{				
					iEmployeeRepository.save(employeeModel);				
				} 
				catch (Exception e) 
				{
					throw new ServiceResourceNotCreatedOrUpdatedException(employeeModel.getFullName(), 
							employeeModel.getEmail());				
				}
				
				log.info("Employee deleted." +employeeUuid);
				
				rabbitMQProducer.sendEvent(employeeUuid, EMPLOYEE_DELETED, ServiceUtil.getDateToString(new Date()));
				
				log.info("Employee deleted event sent. " + employeeUuid);
				
				return ResponseEntity.ok().build();
			}
			
			log.error("Invalid employee data." +employeeUuid);
			
			throw new ServiceResourceNotFoundException("Employee", "employeeUuid", employeeUuid);
		}	
		else
		{		
			log.error("Invalid employee data." +employeeUuid);
			
			throw new ServiceResourceNotFoundException("Employee", "employeeUuid", employeeUuid);
			//return ResponseEntity.badRequest().build();			
		}			
	}
	
	
	public Optional<EmployeeModel> updateEmployee(final String employeeUuid, final EmployeeModel eM)
	{
		ServiceUtil.isNotNullAndNotEmpty(employeeUuid);
		ServiceUtil.isNotNull(eM);
		
		Optional<EmployeeModel> empExist = iEmployeeRepository.findById(employeeUuid);
		
		if ( empExist.orElse(null) != null 
				&& empExist.isPresent()
				&& ServiceUtil.isValidEmailAddress(eM.getEmail()) 
				&& !iEmployeeRepository.findEmployeeEmailList().contains(eM.getEmail()))
		{
			
			employeeModel.setUuid(employeeUuid);
			employeeModel.setBirthday(eM.getBirthday());
			employeeModel.setEmail(eM.getEmail());
			employeeModel.setEmployeeHobby(eM.getEmployeeHobby());
			employeeModel.setFullName(eM.getFullName());			
			employeeModel.setIsDeleted(false);
					
			try 
			{
				iEmployeeRepository.save(employeeModel);
			} 
			catch (Exception e) 
			{
				throw new ServiceResourceNotCreatedOrUpdatedException(employeeModel.getFullName(), employeeModel.getEmail());				
			}	
			
			log.info("Employee data updated." + employeeUuid);
			
			rabbitMQProducer.sendEvent(employeeModel.getUuid(), EMPLOYEE_UPDATED, ServiceUtil.getDateToString(new Date()));
			
			log.info("Employee updated event sent. " + employeeUuid);
			
			return iEmployeeRepository.findById(employeeUuid);
		}	
		else
		{
			log.error("Invalid email." + employeeUuid);
			
			throw new ServiceResourceNotCreatedOrUpdatedException(eM.getFullName(), eM.getEmail());
		}
	}
}
