FROM openjdk:17-jdk-slim

COPY . /app

WORKDIR /app

RUN ./mvnw clean install

EXPOSE 8090

CMD ["java", "-jar", "target/t7_g26.jar"]

