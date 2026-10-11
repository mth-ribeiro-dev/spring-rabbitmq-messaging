# messageria

[English](README.md) | **Português**

Projeto de estudo sobre mensageria com Spring Boot e RabbitMQ. Dois serviços se comunicam por uma fila, em vez de chamarem um ao outro por HTTP:

```
messageriaPedido (:8082) ──► RabbitMQ, fila "request" ──► messageriaEmail (:8081)
```

A explicação completa está em um artigo no blog [matheusr.dev](https://matheusr.dev): **[leia o artigo](https://matheusr.dev/posts/mensageria-o-basico/)**.

> É código de estudo: prioriza a clareza e não está pronto para produção.

## Como rodar

Requisitos: Java 21 e Docker.

1. Clone o repositório:
   ```bash
   git clone https://github.com/mth-ribeiro-dev/spring-rabbitmq-messaging.git && cd spring-rabbitmq-messaging
   ```
2. Suba o RabbitMQ:
   ```bash
   docker run -d --name rabbitmq -p 127.0.0.1:5672:5672 -p 127.0.0.1:15672:15672 rabbitmq:4-management
   ```
3. Suba cada serviço em um terminal, a partir da pasta do repositório:
   ```bash
   cd messageriaEmail && ./mvnw spring-boot:run
   ```
   ```bash
   cd messageriaPedido && ./mvnw spring-boot:run
   ```
4. Crie um pedido:
   ```bash
   curl -X POST http://localhost:8082/api/v1/request
   ```

O serviço de e-mail mostra no log a mensagem que recebeu. Dá para acompanhar a fila em http://localhost:15672 (usuário e senha: `guest`).
