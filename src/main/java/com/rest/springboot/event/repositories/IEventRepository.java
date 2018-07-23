package com.rest.springboot.event.repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rest.springboot.event.models.EventIdentity;
import com.rest.springboot.event.models.EventModel;

@Repository
public interface IEventRepository extends JpaRepository<EventModel, EventIdentity>
{

	public static final String EVENT_TABLE = "t_event";
	
	@Cacheable("employeeEventList")
	@Query(value = "SELECT * FROM " + EVENT_TABLE + " WHERE employee_uuid = ?1 order by event_date", nativeQuery = true)
	List<EventModel> findUserEventList(final String uuid);
}
