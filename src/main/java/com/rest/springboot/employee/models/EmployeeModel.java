package com.rest.springboot.employee.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tEmployee")
@Component
public class EmployeeModel
{
	@Id
	@Column(name = "Uuid", updatable = false, nullable = false)
	private String uuid ;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "FullName")
	private String fullName;
	
	@Column(name = "Birthday")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@ElementCollection
	@CollectionTable(name = "employee_hobby", joinColumns = @JoinColumn(name = "uuid"))
	private Set<String> employeeHobby = new HashSet<>();

	@Column(name = "IsDeleted")
	private boolean isDeleted;
		
	public EmployeeModel() 
	{
		
	}

	public EmployeeModel(String uuid, String email, String fullName, Date birthday, Set<String> employeeHobby,
			boolean isDeleted) {
		super();
		this.uuid = uuid;
		this.email = email;
		this.fullName = fullName;
		this.birthday = birthday;
		this.employeeHobby = employeeHobby;
		this.isDeleted = isDeleted;
	}



	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {      
		this.uuid = uuid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@JsonIgnore
	public boolean getIsDeleted() {
		return isDeleted;
	}	

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Set<String> getEmployeeHobby() 
	{
		return employeeHobby;
	}

	public void setEmployeeHobby(Set<String> employeeHobby) 
	{
		this.employeeHobby = employeeHobby;
	}

	@Override
	public String toString() {
		return "EmployeeModel [uuid=" + uuid + ", fullname=" + fullName + "]";
	}	
}
