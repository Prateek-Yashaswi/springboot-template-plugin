FROM eclipse-temurin:${javaVersion}-jdk-alpine

WORKDIR /app

# Copy the JAR file
COPY target/*.jar application.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "application.jar"]