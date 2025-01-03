# Use a Java 21 base image
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY build/libs/automacorp-0.0.1-SNAPSHOT.jar /app/AutomacorpApplication.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "AutomacorpApplication.jar"]
