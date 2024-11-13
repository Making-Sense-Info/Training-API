# Training API

Java API for training purpose.

## Run locally

Execute `mvn spring-boot:run` to expose API on `http://localhost:8080`.

Swagger interface will also be available on `http://localhost:8080/swagger-ui`.

## Docker

Available on [dockerhub](https://hub.docker.com/r/makingsenseinfo/training-api).

```
docker pull makingsenseinfo/training-api
docker run -p 8080:8080 makingsenseinfo/training-api
```

## Kubernetes

Basic Kubernetes objects are available in [`.kubernetes`](./.kubernetes) folder.