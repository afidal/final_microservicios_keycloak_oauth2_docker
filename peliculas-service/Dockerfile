FROM openjdk:17-jdk-alpine
RUN apk update && apk add bash
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "-t", "90","eureka-server:8761", "--", "java", "-jar", "app.jar"]
EXPOSE 8086