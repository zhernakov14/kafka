FROM openjdk:17-jdk
WORKDIR /app
COPY consumer/target/consumer-0.0.1-SNAPSHOT.jar consumer.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "consumer.jar"]