FROM eclipse-temurin:17-jdk-alpine as base

WORKDIR /app

COPY target/payment-service.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]