package com.github.mth_ribeiro_dev.messageriaEmail.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class EmailController {

    @PostMapping("/email")
    public void sendMail(){
        System.out.println("sent mail");
    }

}
