FROM openjdk:17
EXPOSE 6004
ADD target/email-service.jar email-service.jar
ENTRYPOINT ["java","-jar","email-service.jar"]