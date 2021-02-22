## Java module for mastercarT built using Spring Boot.

### How to run:

1. Run `EcomApplication.java` from the IDE. It will start the embedded tomcat in port 8081.
2. Also implemented the docker. It can also run in an isolated container using docker commands.
3. To run from command prompt.
	i. Build the project using `mvn clean install` from the project base path.
	ii. Once application is built, Change directory to target folder and run `java -jar ecom-0.0.1-SNAPSHOT.jar` from command prompt.


####### API information can be found in `http://localhost:8081/api/swagger-ui.html#/`
	
### Technologies used:
* Spring boot
* Java 8
* SwaggerUI
* Docker