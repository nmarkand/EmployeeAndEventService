# EmployeeService
Spring Boot 

### project using springboot, mysql, hibernate JPA, RabbitMQ

#### install RabbitMQ client and server (if already installed on specific server then need to configure IP in application.properties file, available inside folder structure src/main/resources otherwise local)

#### Create database restservices in mysql db. At the moment hibernate is set to create mode.

#### DB credentials can be found in application.properties file, available inside folder structure src/main/resources

#### build project "mvn clean install " . It will create Jar file EmployeeService.jar and will be available in target folder of the project structure. 

#### Execute java -jar EmployeeService.jar command from command prompt. 

#### ping url http://localhost:8080/swagger-ui.html 
//use following credentials for basicauth in order to access api using swagger. 
username - Emp@serv
password - P@ssword

#### To execute test cases enable following properties in application.properties file.
#### spring.datasource.url = jdbc:h2:mem:test
#### spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect

