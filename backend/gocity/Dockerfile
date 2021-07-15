# Build
FROM gradle:7.1.1-jdk11 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon 

# Run
FROM openjdk:11.0-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/gocity.jar
COPY --from=build /home/gradle/src/profiles/application-docker.properties /app/application-docker.properties

ENTRYPOINT ["java", "-Xms512M", "-Xmx1G", "-jar", "/app/gocity.jar", "--spring.config.location=/app/application-docker.properties", "--spring.profiles.active=docker"]
