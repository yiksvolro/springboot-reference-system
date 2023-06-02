FROM maven:3.8.3-amazoncorretto-16 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:16
WORKDIR /app
COPY --from=builder /build/target/springboot-reference-system-restapi-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]