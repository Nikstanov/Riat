# Microservices Shop System

## Overview

This project demonstrates a microservices-based architecture for an e-commerce system. Each microservice is developed using Java and Spring Boot, offering scalability, modularity, and ease of maintenance.

## Features

- **Authentication and Authorization:** Utilizes Keycloak for robust authentication and authorization mechanisms, securing the microservices and protecting sensitive data.

- **Item Management Service:** Implements a microservice responsible for storing and managing items in the shop. It leverages Spring Security for OAuth integration with Keycloak, Spring JPA for data persistence using PostgreSQL, and Spring Actuator for monitoring.

- **Logger Service:** Centralized logging service that aggregates logs from all microservices, providing a comprehensive view of the system's activity.

- **Metrics and Monitoring:** Integrates Prometheus with Spring Actuator for collecting microservices metrics. Grafana is used for visualizing and analyzing the metrics, ensuring system health and performance.

- **Message Broker:** Employs Kafka as a message broker for asynchronous communication between microservices. This ensures decoupling and scalability while maintaining data consistency.

- **Service Discovery:** Utilizes Spring Eureka for service discovery, enabling microservices to dynamically locate and communicate with each other. This enhances the flexibility and robustness of the system.

- **Gateway:** Implements a Spring Gateway for a unified entry point into the system. This gateway simplifies client interactions and manages the routing of requests to the appropriate microservices.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Keycloak
- Spring Data JPA
- PostgreSQL
- Spring Actuator
- Kafka
- Spring Eureka
- Spring Gateway
- Prometheus
- Grafana

## Getting Started

To run the project locally, follow these steps:

1. Clone the repository: `git clone [repository_url]`
2. Navigate to the project directory: `cd [project_directory]`
3. Build and run each microservice using Gradle or your preferred build tool.
4. Use Docker Compose to run the entire project: `docker-compose up`
   - The entry point is available at [http://localhost:8080](http://localhost:8080)
   - Grafana runs on port 3000: [http://localhost:3000](http://localhost:3000)
   - Kafka UI for debugging is accessible at [http://localhost:8090](http://localhost:8090)
   - Keycloak is available at [http://localhost:8091](http://localhost:8091)
