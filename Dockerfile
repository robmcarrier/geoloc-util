FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml verify package
RUN ls /usr/src/app/target

#
# PACKAGE STAGE
FROM openjdk:21-buster
COPY --from=build /usr/src/app/target/geoloc-util-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/app/java.jar
RUN ls /usr/app
ENTRYPOINT ["java", "-jar", "/usr/app/java.jar"]