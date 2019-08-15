package com.carl.exportexcle.controller;


import com.carl.exportexcle.common.provider.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private MsgProducer producer;

    @RequestMapping(value = "/mq")
    public String testMQ(){
        producer.sendMsg("test123");
        return "";
    }


}
