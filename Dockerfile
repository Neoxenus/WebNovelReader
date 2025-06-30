FROM openjdk:24-jdk
WORKDIR /app
COPY target/WebNovelReader-0.0.1-SNAPSHOT.jar /app/WebNovelReader.jar
ENTRYPOINT ["java", "-jar", "WebNovelReader.jar"]