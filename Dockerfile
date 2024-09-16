# Base image with OpenJDK
FROM openjdk:17-jdk-alpine

# Working directory inside the container
WORKDIR /account-management-service
# Copy the JAR file to the container
COPY target/account-management-service-0.0.1-SNAPSHOT.jar account-management-service.jar

# Port the app will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "account-management-service.jar"]
