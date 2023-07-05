# Use a base image with Java 17 and Maven pre-installed
FROM openjdk:17-jdk-slim as build

# Install Maven
RUN apt-get update && \
    apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download the Maven dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests -DoutputDirectory=/app/target

# Create a new image with a smaller runtime base image
FROM openjdk:17-jdk-slim

# Install Maven in the runtime stage
RUN apt-get update && \
    apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar /app/T7_G26-1.0.0.jar

# Copy the resources directory to the container
COPY src/main/resources /app/src/main/resources

# copy pom.xml from context into image
COPY pom.xml /app/pom.xml

EXPOSE 8090

CMD ["java", "-jar", "T7_G26-1.0.0.jar"]

