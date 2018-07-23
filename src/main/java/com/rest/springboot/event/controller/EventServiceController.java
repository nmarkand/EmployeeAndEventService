package com.rest.springboot.event.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest.springboot.event.models.EventModel;
import com.rest.springboot.event.repositories.IEventRepository;

@RestController
@RequestMapping("/api")
public class EventServiceController 
{
	@Autowired
	IEventRepository iEventRepository;
	
	@GetMapping("/employeeevent/uuid/{uuid}")
	public List<EventModel> getEmployeeEventListByEmployeeUuid(
			@PathVariable(value = "uuid") final String uuid) throws Exception 
	{				
		return iEventRepository.findUserEventList(uuid);		
	}
	
	
	
}
