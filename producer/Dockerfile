FROM openjdk:17-jdk
WORKDIR /app
COPY producer/target/producer-0.0.1-SNAPSHOT.jar producer.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "producer.jar"]