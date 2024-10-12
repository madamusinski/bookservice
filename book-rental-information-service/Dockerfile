FROM amazoncorretto:21-alpine-jdk
LABEL authors="Mateusz"
WORKDIR /app
COPY target/book-rental-information-service-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "/app/book-rental-information-service-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]