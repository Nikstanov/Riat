FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY stats_service/build/libs/stats_service-0.0.1-SNAPSHOT.jar stats_service.jar

CMD ["java", "-jar", "stats_service.jar"]