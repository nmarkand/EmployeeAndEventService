package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// How spring boot know this is a start point of application

@SpringBootApplication

//all below three steps annotaions are done in one single step annotaion @SpringBootApplication. 
//@Configuration  // ApplicationContext creation  
//@ComponentScan  //Scan different component scan Bean, repository, @controller etc.
//@EnableAutoConfiguration  //--Spring Boot auto-configuration attempts to automatically configure your 
						  //Spring application based on the jar dependencies that you have added. For example, 
						 //if HSQLDB is on your class path, and you have not manually configured any database connection
                         //beans, then Spring Boot auto-configures an in-memory database.
public class SpringBootStart // starter class must be in most outer package.
{
	//Spring boot start starts execute because its an standalone application servlet 
	//container starts and host application
	//because spring boot needs an start point to boot strap application
	public static void main(String[] args) 
	{
		//start app, create servlet container and host application
		SpringApplication.run(SpringBootStart.class, args);
	}

}
