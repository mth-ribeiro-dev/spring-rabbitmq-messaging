# messageria

**English** | [Português](README.pt-BR.md)

A learning project about messaging with Spring Boot and RabbitMQ. Two services talk through a queue instead of calling each other over HTTP:

```
messageriaPedido (:8082) ──► RabbitMQ, queue "request" ──► messageriaEmail (:8081)
```

The full explanation is in an article on the [matheusr.dev](https://matheusr.dev) blog: **[read the article](https://matheusr.dev/posts/mensageria-o-basico/)** (in Portuguese).

> This is study code: it favors clarity and is not production-ready.

## Running it

Requirements: Java 21 and Docker.

1. Clone the repository:
   ```bash
   git clone https://github.com/mth-ribeiro-dev/spring-rabbitmq-messaging.git && cd spring-rabbitmq-messaging
   ```
2. Start RabbitMQ:
   ```bash
   docker run -d --name rabbitmq -p 127.0.0.1:5672:5672 -p 127.0.0.1:15672:15672 rabbitmq:4-management
   ```
3. Start each service in its own terminal, from the repository folder:
   ```bash
   cd messageriaEmail && ./mvnw spring-boot:run
   ```
   ```bash
   cd messageriaPedido && ./mvnw spring-boot:run
   ```
4. Create an order:
   ```bash
   curl -X POST http://localhost:8082/api/v1/request
   ```

The email service logs the message it received. You can watch the queue at http://localhost:15672 (user and password: `guest`).
