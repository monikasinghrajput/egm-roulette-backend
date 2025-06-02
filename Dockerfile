# Stage 1: Build the application
FROM openjdk:21-slim AS build

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set work directory
WORKDIR /home/app

# Copy Maven project files
COPY pom.xml . 
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:21-slim

# Create mountable volume and expose port
VOLUME /tmp
EXPOSE 9090

# Copy the built jar from the build stage
COPY --from=build /home/app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
