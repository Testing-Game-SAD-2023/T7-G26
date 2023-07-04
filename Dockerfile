FROM openjdk:17-jdk-slim

COPY . /app

WORKDIR /app

EXPOSE 8090

CMD ["java", "-jar", "target/T7_G26-1.0.0.jar"]

