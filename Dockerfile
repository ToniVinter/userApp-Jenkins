FROM openjdk:8-jdk-alpine
MAINTAINER antoniovinter.com
EXPOSE 8081
ADD implementation/target/implementation-0.0.1-SNAPSHOT.jar implementation-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/implementation-0.0.1-SNAPSHOT.jar"]
