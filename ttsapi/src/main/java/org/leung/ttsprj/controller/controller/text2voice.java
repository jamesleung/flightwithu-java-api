package org.leung.ttsprj.controller.controller;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import org.leung.ttsprj.service.AircraftInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class text2voice {
    public static final String APP_ID = "18559992";
    public static final String API_KEY = "IetBxB7QsvvrQB5SQWxIpCFz";
    public static final String SECRET_KEY = "q4CivyyiQEC6nBUauapnnDoN1NDtLgQ5";

    @Autowired
    AircraftInfoService aircraftInfoService;

    private byte[] getResponseFromAip(String origText, HashMap<String, Object> options) {
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(10000);

        // 调用接口
        HashMap<String, Object> options1 = new HashMap<String, Object>();
        options.put("spd", "7");
        options.put("pit", "15");
        options.put("vol", "6");
        options.put("per", "4");
        TtsResponse res = client.synthesis(origText, "zh", 1, options1);

        try {
            return res.getData();
        } catch (Exception e) {
            System.out.println(res.getResult().toString(2));
            return null;
        }
    }

    private void getVoiceFile(byte[] bytes) throws IOException {
        File file = new File("result.mp3"); // 打开mp3文件即可播放
        FileOutputStream os = new FileOutputStream(file);
        os.write(bytes);
        os.close();
        System.out.println("audio file write to " + file.getAbsolutePath());
    }

    @RequestMapping(value="/text2Voice")
    public void text2Voice(String origText, HashMap<String, Object> options) throws IOException {
        byte[] data = getResponseFromAip(origText, options);
        getVoiceFile(data);
    }

    @RequestMapping(value="/helo")
    public String index() {
        return "Hello Spring!";
    }

    @RequestMapping(value="/helo1")
    public void twoNumberExchangeValue() {
        int a = 15;
        int b  = 199;
        System.out.println("交换前的值：a = " + a + "; b = " + b);
        a = a^b;
        b = b^a;
        a = a^b;
        System.out.println("交换后的值：a = " + a + "; b = " + b);
    }

    /*

     */
    @RequestMapping(value = "/getflight")
    public String getFlightInfo(@RequestParam(value = "no") String flightNo, @RequestParam(value = "i", defaultValue = "0") int index) {
        return aircraftInfoService.getAircraft(flightNo, index);
    }
}
