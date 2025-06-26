FROM openjdk:25-ea-23-jdk-bullseye
WORKDIR /app
COPY /out/artifacts/WebNovelReader_jar/WebNovelReader.jar /app/WebNovelReader.jar
ENTRYPOINT ["java", "-jar", "LessonJPA.jar"]