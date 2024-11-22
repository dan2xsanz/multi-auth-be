FROM openjdk:8
EXPOSE 8080
ADD target/snz-backend-image.jar snz-backend-image.jar
ENTRYPOINT ["java", "-jar", "/snz-backend-image.jar"]