package com.github.mth_ribeiro_dev.messageriaPedido.controller;

import com.github.mth_ribeiro_dev.messageriaPedido.config.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class RequestController {

    private final MessagePublisher messagePublisher;
    private static final Logger log = LoggerFactory.getLogger(RequestController.class);


    public RequestController(MessagePublisher messagePublisher){
        this.messagePublisher = messagePublisher;
    }

    @PostMapping("/request")
    public void request(){
        messagePublisher.enqueue("order created");
        log.info("request send");
    }
}
