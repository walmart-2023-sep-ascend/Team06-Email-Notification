FROM openjdk:17
WORKDIR /app
COPY target/email-service.jar /app/email-service.jar
COPY src/main/resources/static /app/static
EXPOSE 9503
CMD ["java","-jar","email-service.jar"]