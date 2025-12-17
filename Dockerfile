FROM eclipse-temurin:23-jdk-alpine

EXPOSE 8080

COPY target/training.api-*.jar training-api.jar

ENTRYPOINT ["java", "-jar", "training-api.jar"]