package com.rest.springboot.event.repositories;

import org.springframework.stereotype.Repository;
import com.rest.springboot.event.models.EventModel;

@Repository
public interface IEventServiceRepository 
{
	public EventModel createEvent(final String message);
}
