package org.leung.ttsprj.service;

import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
            redisTemplate.expire(cacheKey, 7, TimeUnit.DAYS);
        }
    }

    /*
    flightNo : 航班号
    index : 坐标集读取位置
     */
    public String getAircraft(String flightNo, int index) {
        List<String> coordinates = new ArrayList();
        List<JSONObject> acObjects = redisTemplate.opsForList().range(flightNo, index, -1);
        for (JSONObject acObject : acObjects) {
            coordinates.add(new StringBuilder()
                    .append(acObject.getString("lat"))
                    .append(",")
                    .append(acObject.getString("lon"))
                    .toString());
        }
        return JSONObject.toJSONString(coordinates);

        //List<JSONObject> acObject = (List<JSONObject>)acObjects;

        /*dynastic coordinate, test code*/
        //JSONObject acObject = (JSONObject)redisTemplate.opsForList().leftPop(flightNo);
        /*
        return new StringBuilder()
                .append(acObject.getString("lat"))
                .append(",")
                .append(acObject.getString("lon"))
                .toString();

         */
    }
}
