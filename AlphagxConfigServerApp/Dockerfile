FROM openjdk:8
VOLUME /tmp
EXPOSE 8888
ARG JAR_FILE
COPY target/${JAR_FILE} /spring-cloud-config-server.jar 
ENTRYPOINT [ "sh", "-c", "java -jar /spring-cloud-config-server.jar" ]