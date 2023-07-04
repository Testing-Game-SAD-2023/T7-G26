FROM adoptopenjdk:17.0.6_jdk-hotspot

COPY . /app

WORKDIR /app

RUN ./mvnw clean install

EXPOSE 8090

CMD ["java", "-jar", "target/nombre-proyecto.jar"]

