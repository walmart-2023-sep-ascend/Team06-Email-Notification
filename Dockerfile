FROM openjdk:17
EXPOSE 9503
ADD target/email-service.jar email-service.jar
ENTRYPOINT ["java","-jar","email-service.jar"]