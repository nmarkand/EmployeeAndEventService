package com.rest.springboot.event.service;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import com.rest.springboot.event.models.EventIdentity;
import com.rest.springboot.event.models.EventModel;
import com.rest.springboot.event.repositories.IEventRepository;
import com.rest.springboot.event.repositories.IEventServiceRepository;
import com.rest.springboot.exceptions.ServiceResourceNotCreatedOrUpdatedException;
import com.rest.springboot.utils.ServiceUtil;

@Service
public class EventService implements IEventServiceRepository
{
	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(EventModel.class);
	
	@Autowired
	EventIdentity eventIdentity;
	
	@Autowired
	EventModel eventModel;
	
	@Autowired
	IEventRepository iEventRepository;
	@CacheEvict(cacheNames="employeeEventList", allEntries=true)
	public EventModel createEvent(final String message) 
	{	
		final JSONObject jsonObject = ServiceUtil.parseJsonObject(message);
		
		final String employeeUuid = ServiceUtil.getJsonObjectValue(jsonObject, "employeeuuid");
		final String eventType = ServiceUtil.getJsonObjectValue(jsonObject, "eventtype");
		final String eventDate = ServiceUtil.getJsonObjectValue(jsonObject, "eventdate");
		
		eventIdentity.setEmployeeUuid(employeeUuid);
		eventIdentity.setEventType(eventType);
		eventIdentity.setEventDate(ServiceUtil.getStringToDate(eventDate));
		eventModel.setEventIdentity(eventIdentity);
			
		try 
		{
			return iEventRepository.save(eventModel);
		} 
		catch (Exception e) 
		{
			log.error("Error in event creation.");
			
			throw new ServiceResourceNotCreatedOrUpdatedException(eventModel.getEventIdentity().getEmployeeUuid(), 
					eventModel.getEventIdentity().getEventType());
		}
	}
}
