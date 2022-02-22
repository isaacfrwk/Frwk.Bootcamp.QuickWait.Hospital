FROM openjdk:11-slim

LABEL maintainer="Daniel Dutra <danieldhsd@gmail.com>"

ENTRYPOINT ["java", "-jar", "/app/hospital-service.jar"]

ARG JAR_FILE

ADD ${JAR_FILE} /app/hospital-service.jar
