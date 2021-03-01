FROM openjdk:8-jdk-alpine
ADD target/backend-service-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","-Djava.net.preferIPv4Stack=true", "/app.jar"]