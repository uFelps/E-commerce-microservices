FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar
CMD ["java", "-jar", "/app/gateway.jar"]