FROM maven:3.8.1-jdk-11-slim AS build

WORKDIR /app/backend

COPY pom.xml /app/backend
COPY src /app/backend/src

RUN mvn -f pom.xml clean package

FROM openjdk:11

WORKDIR /app/backend

COPY --from=build /app/backend/target/*.jar /app/backend/app.jar

ENTRYPOINT ["java","-jar","app.jar"]