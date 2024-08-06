FROM openjdk:17-jdk-slim
WORKDIR /usr/local/app
COPY target/kinopoisk-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]