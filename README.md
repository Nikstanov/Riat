# Riat
Riat project - java microservices

Микросервисная архитектура

Стек:

    Java  
    Spring
    Keycloak - authorization and authentication
    Spring cloud gateway
    Spring cloud eureka
    prometheus + grafana
    Kafka - broker

# Services

Api gateway

    Единая точна входа
    Порт 8080

Inventory service

    GET /api/v1/items/get - получить все предметы
    POST /api/v1/items/add - добавить новые предметы

Services registry Eureka

    Все сервисы в системе
    Порт 8082

Keycloak 

    Сервис аунтефикации и авторизации
    Юзер админ, пароль админ
    Порт 8091

KafkaUI

    Порт 8090

Grafana

    Порт 3000
