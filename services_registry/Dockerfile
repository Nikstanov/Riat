FROM openjdk:17-jdk-alpine

WORKDIR /app

# Копирование JAR-файла в контейнер
COPY services_registry/build/libs/services_registry-0.0.1-SNAPSHOT.jar service_registry.jar

# Команда для запуска JAR-файла
CMD ["java", "-jar", "service_registry.jar"]