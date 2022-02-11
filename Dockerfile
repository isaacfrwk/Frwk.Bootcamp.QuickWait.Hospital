FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/hospital-0.0.1-SNAPSHOT.jar /app/hospital-api.jar

EXPOSE 8080

CMD ["java", "-jar", "hospital-api.jar"]
