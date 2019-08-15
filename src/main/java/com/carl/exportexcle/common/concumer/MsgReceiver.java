package com.carl.exportexcle.common.concumer;


import com.carl.exportexcle.common.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver {
    @RabbitHandler
    public void process(String content){
        System.out.println("收到队列中的消息："+content);
    }
}