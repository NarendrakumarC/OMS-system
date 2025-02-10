FROM  openjdk:17-jdk-slim 
WORKDIR /app
COPY target/ots-service-1.0.jar ots-service-1.0.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "ots-service-1.0.jar"]
