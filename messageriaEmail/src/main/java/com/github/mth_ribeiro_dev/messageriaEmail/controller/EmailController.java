package com.github.mth_ribeiro_dev.messageriaEmail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1")
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    @RabbitListener(queues = "request")
    public void receive(String message){
        log.info("sent mail: {}", message);
    }

}
