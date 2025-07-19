# BUILDER
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# DIRECTORY
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw package -DskipTests


FROM eclipse-temurin:21-jre-jammy

WORKDIR /app


COPY --from=builder /app/target/*.jar app.jar

#LISTENING PORT
EXPOSE 8080

#LAUNCHING APPLICATION
ENTRYPOINT ["java", "-jar", "app.jar"]b"]