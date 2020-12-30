FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8761
ARG JAR_FILE
COPY target/${JAR_FILE} /spring-eureka-server.jar 
##ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar /spring-eureka-server.jar" ]