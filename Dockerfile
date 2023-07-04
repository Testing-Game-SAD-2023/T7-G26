FROM adoptopenjdk:17.0.6_jdk-hotspot

COPY . /app

WORKDIR /app

EXPOSE 8090

CMD ["java", "-jar", "target/T7_G26-1.0.0.jar"]

