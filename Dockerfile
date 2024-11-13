FROM openjdk:23-jdk-slim
EXPOSE 8080

COPY target/training.api-*.jar training-api.jar

ENTRYPOINT ["java", "-jar", "training-api.jar"]