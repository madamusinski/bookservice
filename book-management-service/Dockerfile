FROM amazoncorretto:21-alpine-jdk
LABEL authors="Mateusz"
WORKDIR /app
COPY target/book-management-service-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "/app/book-management-service-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]