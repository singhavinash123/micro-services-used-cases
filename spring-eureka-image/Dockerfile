FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8100
ARG JAR_FILE
COPY target/${JAR_FILE} /spring-eureka-image.jar 
ENTRYPOINT [ "sh", "-c", "java -jar /spring-eureka-image.jar" ]