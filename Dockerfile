FROM amazoncorretto:16
WORKDIR /app
COPY target/springboot-reference-system-restapi-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]