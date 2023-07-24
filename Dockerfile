FROM openjdk:8-jdk-alpine

EXPOSE 9091

ADD target/vaccination-service.jar vaccination-service.jar

ENTRYPOINT ["java","-jar","vaccination-service.jar"]