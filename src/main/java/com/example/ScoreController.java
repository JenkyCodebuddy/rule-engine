package com.example;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.Calc;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @Autowired
    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();
    public String json;
    //test

    @RequestMapping(method = RequestMethod.POST)
    public ScoreModel test(){
        httpRequest http = new httpRequest();                   //call sonarqube api when POST request is made
        try{
            json = http.sendPost();
        }
        catch(Exception e){
            System.out.println("Oops!");
        }
        return new ScoreModel(1,"test");
    }


    @RequestMapping(method = RequestMethod.GET)
    public ScoreModel score() {
        String[] val = {"100", "1", "1", "1", "1", "1", "1"};
        Calc calculator = new Calc(val);
        return new ScoreModel(counter.incrementAndGet(), String.format(template, calculator.generateScore()));
    }
}