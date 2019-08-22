FROM openjdk:8-jre-slim
ENV JAR_FILE /SpringbootCourse/catalogos-web/target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]