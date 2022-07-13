package com.zerox.chapter2.example;

import com.zerox.chapter2.Controller;
import com.zerox.chapter2.HttpRequest;
import com.zerox.chapter2.HttpResponse;
import com.zerox.chapter2.StrategyView;
import com.zerox.chapter2.TinyWeb;

import java.util.Collections;
import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 14:02
 */
public class Example {
    public static void main(String[] args) {
        HashMap<String, Controller> map = new HashMap<>();
        map.put("/greeting", new GreetingController(new StrategyView(new GreetingRenderingStrategy())));
        TinyWeb tinyWeb = new TinyWeb(map, Collections.singletonList(new LoggingFilter()));
        HttpRequest req = HttpRequest.Builder.newBuilder().body("Mike,Joe,John,Steve").path("/greeting").build();
        HttpResponse resp = tinyWeb.handleRequest(req);
        System.out.println("resp.getBody() = " + resp.getBody());
        System.out.println("resp.getResponseCode() = " + resp.getResponseCode());
    }
}
