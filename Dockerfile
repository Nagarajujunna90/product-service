FROM openjdk:8-jdk-alpine
EXPOSE 8086
ADD target/product-service-0.0.1-SNAPSHOT.jar product-service
ENTRYPOINT ["java","-jar","product-service"]