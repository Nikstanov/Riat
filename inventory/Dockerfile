FROM openjdk:17-jdk-alpine

WORKDIR /app

# Копирование JAR-файла в контейнер
COPY inventory/build/libs/inventory-0.0.1-SNAPSHOT.jar inventory.jar

# Команда для запуска JAR-файла
CMD ["java", "-jar", "inventory.jar"]