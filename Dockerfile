FROM openjdk:8
WORKDIR usr/src
ADD ./target/ecom-0.0.1-SNAPSHOT.jar /usr/src/ecom-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/src/ecom-0.0.1-SNAPSHOT.jar"]