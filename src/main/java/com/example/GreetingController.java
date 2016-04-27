package com.example;

import java.util.concurrent.atomic.AtomicLong;

import com.example.GreetingModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.Calc;

@RestController
public class GreetingController {

    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();
    //test

    @RequestMapping("/score")
    public GreetingModel score() {
        String[] val = {"1","1","1","1","1","1","1"};
        Calc calculator = new Calc(val);
        return new GreetingModel(counter.incrementAndGet(), String.format(template,calculator.generateScore()));
    }
}