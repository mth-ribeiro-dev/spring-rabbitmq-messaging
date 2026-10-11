package com.github.mth_ribeiro_dev.messageriaPedido.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;


@Component
public class MessagePublisher {

    private static final Logger log = LoggerFactory.getLogger(MessagePublisher.class);

    private final ConcurrentLinkedQueue<String> pending = new ConcurrentLinkedQueue<>();

    private final RabbitTemplate rabbitTemplate;

    public MessagePublisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enqueue(String message){
        pending.add(message);
    }

    @Scheduled(fixedDelay = 5000)
    public void publishPending(){
        log.info("publishPending running, {} pending", pending.size());
        while(!pending.isEmpty()){
            String message = pending.peek();
            try{
                CorrelationData correlation = new CorrelationData();
                rabbitTemplate.convertAndSend("","request", message, correlation);

                CorrelationData.Confirm confirm = correlation.getFuture().orTimeout(10, TimeUnit.SECONDS).join();

                if (confirm.ack() && correlation.getReturned() == null){
                    pending.poll();
                } else {
                    log.warn("RabbitMQ did not accept the message; retrying in 5 s");
                    return;
                }
            }catch (AmqpException | CompletionException e){
                log.warn("RabbitMQ unavailable; retrying in 5s: {}", e.getMessage());
                return;
            }
        }
    }
}
