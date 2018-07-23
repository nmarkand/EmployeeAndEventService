# EventService
Spring Boot 

### project using springboot, mysql, hibernate JPA, RabbitMQ

#### At the moment hibernate is set to create mode.

#### DB credentials can be found in application.properties file, available inside folder structure src/main/resources

#### build project "mvn clean install ". It will create Jar file EventService.jar and will be available in target folder of the project structure. 

#### Execute java -jar EventService.jar command from command prompt. 

#### ping url http://localhost:8090/swagger-ui.html 

#### To execute test cases enable following properties in application.properties file.
#### spring.datasource.url = jdbc:h2:mem:test
#### spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect
