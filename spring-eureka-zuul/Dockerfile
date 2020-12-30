FROM openjdk:8
EXPOSE 8762
ARG JAR_FILE
COPY target/${JAR_FILE} /spring-eureka-zuul.jar 
##ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar /spring-eureka-zuul.jar" ]