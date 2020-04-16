package org.leung.ttsprj.service;

import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AircraftInfoService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void processAircraft(String message) {
        JSONObject acObject = JSONObject.parseObject(message);
        //ValueOperations<String, Ojbect> operations1 = redisTemplate.opsForSet();

        String cacheKey = acObject.getString("flight");
        if (!StringUtil.isNullOrEmpty(cacheKey)) {
            cacheKey = cacheKey.trim();
            //operations.set(cacheKey, acObject);
            redisTemplate.opsForList().rightPush(cacheKey, acObject);
        }
    }

    public String getAircraft(String flightNo) {
        /*
        List<JSONObject> acObjects = redisTemplate.opsForList().range(flightNo, 0, -1);
        JSONObject acObject = (JSONObject)acObjects.get(0);
         */
        /*dynastic coordinate, test code*/
        JSONObject acObject = (JSONObject)redisTemplate.opsForList().leftPop(flightNo);
        return new StringBuilder()
                .append(acObject.getString("lat"))
                .append(",")
                .append(acObject.getString("lon"))
                .toString();
    }
}
