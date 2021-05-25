FROM openjdk:11-jre-slim-buster
EXPOSE 80
COPY build/libs/books-service.jar books-service.jar
ENTRYPOINT ["java","-jar","/books-service.jar"]