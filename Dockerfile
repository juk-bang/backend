FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} jukbang.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jukbang.jar"]