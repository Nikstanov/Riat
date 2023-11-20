FROM openjdk:17-jdk-alpine

WORKDIR /app

# Копирование JAR-файла в контейнер
COPY api_gateway/build/libs/api_gateway-0.0.1-SNAPSHOT.jar api_gateway.jar

# Команда для запуска JAR-файла
CMD ["java", "-jar", "api_gateway.jar"]