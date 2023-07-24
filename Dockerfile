#FROM maven:3-jdk-11 as builder
#RUN mvn clean install
FROM openjdk:17
EXPOSE 8084
ADD target/product-service-0.0.1-SNAPSHOT.jar product-service
ENTRYPOINT ["java","-jar","product-service"]