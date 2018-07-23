package com.rest.springboot.test.event;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.rest.springboot.event.controller.EventServiceController;
import com.rest.springboot.event.models.EventIdentity;
import com.rest.springboot.event.models.EventModel;
import com.rest.springboot.event.repositories.IEventRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class EventControllerTest 
{		     
	private EventModel eventModel;

	private String uuid = null;
	private String eventType = null;
	private Date date = null;

	@Autowired
	private IEventRepository er;
	
	//Create mock object of controller and injects associated mocks.
	@InjectMocks
	private EventServiceController eventServiceController;
	
	@Before
	public void setUp()
	{	
		this.eventModel = new EventModel();
		
		EventIdentity ei = new EventIdentity();
		
		this.uuid = "ABC";
		this.eventType = "CREATED";
		this.date = new Date();
		
		ei.setEmployeeUuid(uuid);
		ei.setEventDate(date);
		ei.setEventType(eventType);
		
		eventModel.setEventIdentity(ei);
	}
	
	@Test
	public void testAddAndGetEvent() throws Exception 
	{			
		er.save(this.eventModel);
	    
	    assertEquals("Type",(er.findUserEventList(uuid).get(0).getEventIdentity().getEventType()), eventType);
	    assertEquals("uuid",(er.findUserEventList(uuid).get(0).getEventIdentity().getEmployeeUuid()), this.uuid);
	    assertEquals("uuid",(er.findUserEventList(uuid).get(0).getEventIdentity().getEventDate()), date);
	    }
	
}
