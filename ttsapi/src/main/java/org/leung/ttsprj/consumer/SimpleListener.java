package org.leung.ttsprj.consumer;

import com.alibaba.fastjson.JSONObject;
import org.leung.ttsprj.service.AircraftInfoService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {

    @Autowired
    AircraftInfoService aircraftInfoService;

    @RabbitListener(queues = "python-test6")
    public void receiveMessage(String message) {
        System.out.println("接收到消息：" + message);
        aircraftInfoService.processAircraft(message);
    }
}
