FROM alpine/java:21-jdk
LABEL maintainer="shekhar.alpula@gmail.com"

WORKDIR /app

EXPOSE 8080

ARG JAR_FILE=./target/ProductServiceNov24-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar" , "app.jar"]