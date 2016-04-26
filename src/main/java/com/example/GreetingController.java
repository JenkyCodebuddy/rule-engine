package com.example;

import java.util.concurrent.atomic.AtomicLong;

import com.example.GreetingModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.Calc;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    //test

    @RequestMapping("/greeting")
    public GreetingModel greeting(@RequestParam(value="name", defaultValue="World") String name) {
        String[] val = {"1","1","1","1","1","1","1"};
        Calc calculator = new Calc(val);
        return new GreetingModel(counter.incrementAndGet(),
                String.format(template, calculator.generateScore()));
    }
}