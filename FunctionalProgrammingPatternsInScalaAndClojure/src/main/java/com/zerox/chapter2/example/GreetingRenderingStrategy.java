package com.zerox.chapter2.example;

import com.zerox.chapter2.RenderingStrategy;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:23
 */
public class GreetingRenderingStrategy implements RenderingStrategy {
    @Override
    public String renderView(Map<String, List<String>> model) {
        List<String> greetings = model.get("greetings");
        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<h1>Friendly Greetings:</h1>\n");
        for (String greeting: greetings) {
            responseBody.append(String.format("<h2>%s</h2>\n", greeting));
        }
        return responseBody.toString();
    }
}
