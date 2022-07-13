package com.zerox.chapter2.example;

import com.zerox.chapter2.HttpRequest;
import com.zerox.chapter2.TemplateController;
import com.zerox.chapter2.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:18
 */
public class GreetingController extends TemplateController {
    private Random random;

    public GreetingController(View view) {
        super(view);
        random = new Random();
    }

    @Override
    protected Map<String, List<String>> doRequest(HttpRequest request) {
        Map<String, List<String>> helloModel = new HashMap<>();
        helloModel.put("greetings", generateGreetings(request.getBody()));
        return helloModel;
    }

    private List<String> generateGreetings(String namesCommaSeperated) {
        String[] names = namesCommaSeperated.split(",");
        List<String> greetings = new ArrayList<>();
        for (String name : names) {
            greetings.add(makeGreeting(name));
        }
        return greetings;
    }

    private String makeGreeting(String name) {
        String[] greetings = {"Hello", "Greetings", "Salutations", "Hola"};
        String greetingPrefix = greetings[random.nextInt(4)];
        return String.format("%s, %s", greetingPrefix, name);
    }
}
