# Use official OpenJDK 17 image
FROM openjdk:17-jdk-alpine

# Add the Spring Boot JAR to the container
COPY target/airline-management-service.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
