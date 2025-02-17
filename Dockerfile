
FROM openjdk:21-jdk-slim as builder

WORKDIR /app


COPY target/springbackend-0.0.1-SNAPSHOT.jar /app/app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/db_demo
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=runna
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080 5005
