FROM eclipse-temurin:17-jdk-alpine as base

WORKDIR /app

COPY target/subscription-service.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]