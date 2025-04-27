# First Stage: Build the application
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy all project files into the container
COPY . .

# Build the application and skip tests
RUN mvn clean package -DskipTests


# Second Stage: Run the application
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
