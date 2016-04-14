package com.example;

import java.util.concurrent.atomic.AtomicLong;

import com.example.GreetingModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/greeting")
    public GreetingModel greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new GreetingModel(counter.incrementAndGet(),
                String.format(template, name));
    }

    public void blabla(){
        int i = 0;
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;

    }

    public void blabla2(){
        int i = 0;
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        System.out.println("asd");
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;

    }

}