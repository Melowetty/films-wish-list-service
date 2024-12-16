FROM openjdk:17-jdk-slim

COPY build/libs/app-standalone.jar .

ENTRYPOINT ["java", "-jar", "app-standalone.jar"]