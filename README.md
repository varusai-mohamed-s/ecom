## Java module for shopping cart application built using Spring Boot.

### How to run:

1. Run `EcomApplication.java` from the IDE. It will start the embedded tomcat in port 8081.
2. Also implemented the docker. It can also run in an isolated container using docker commands.
    * Build the docker image by running `docker image build -t shopping-cart .`
    * Run the container by running `docker container run -p 8081:8081 -d --name shopping-cart-container shopping-cart`
3. To run from command prompt.
    * Build the project using `mvn clean install` from the project base path.
    * Once application is built, Change directory to target folder and run `java -jar ecom-0.0.1-SNAPSHOT.jar` from command prompt.


API information can be found in `http://localhost:8081/api/swagger-ui.html#/`
	
### Technologies used:
* Spring boot
* Java 8
* SwaggerUI
* Docker
* In memory h2 database

The UI for this shopping cart application is developed using Angular 11. It is available in `https://github.com/varusai-mohamed-s/ecom-app`