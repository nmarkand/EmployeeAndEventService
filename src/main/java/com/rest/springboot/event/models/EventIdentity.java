package com.rest.springboot.event.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Embeddable
@Component
public class EventIdentity implements Serializable
{
	@NotNull
	private String employeeUuid ;
	
	@NotNull
	private String eventType;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date eventDate;
	
	public EventIdentity()
	{
		
	}
	
	public EventIdentity(@NotNull String employeeUuid, @NotNull String eventType, @NotNull Date eventDate) {
		super();
		this.employeeUuid = employeeUuid;
		this.eventType = eventType;
		this.eventDate = eventDate;
	}



	public String getEmployeeUuid() {
		return employeeUuid;
	}

	public void setEmployeeUuid(String employeeUuid) {
		this.employeeUuid = employeeUuid;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
}
