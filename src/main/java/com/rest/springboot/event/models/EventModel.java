package com.rest.springboot.event.models;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "tEvent")
@Component
public class EventModel 
{
	@EmbeddedId
    private EventIdentity eventIdentity;
	
	public EventModel()
	{
		
	}

	public EventModel(EventIdentity eventIdentity, Date eventDate )
	{
		this.eventIdentity = eventIdentity;
	}
	
	public EventIdentity getEventIdentity() 
	{
		return eventIdentity;
	}

	public void setEventIdentity(EventIdentity eventIdentity) 
	{
		this.eventIdentity = eventIdentity;
	}
	
	@Override
	public String toString() {
		return "EventModel [eventIdentity=" + eventIdentity + "]";
	}
}
