package com.example;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.Calc;

@RestController
public class ScoreController {
    @Autowired
    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();
    //test

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public String logs(@RequestParam("json") String json) {
        return json;
    }/*
    public ScoreModel score() {
        *//*String[] val = {"1", "1", "1", "1", "1", "1", "1"};
        Calc calculator = new Calc(val);
        return new ScoreModel(counter.incrementAndGet(), String.format(template, calculator.generateScore()));*//*
        return null;
    }*/
}