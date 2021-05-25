FROM openjdk:11-jre-slim-buster
EXPOSE 80
COPY build/libs/users-service.jar users-service.jar
ENTRYPOINT ["java","-jar","/users-service.jar"]