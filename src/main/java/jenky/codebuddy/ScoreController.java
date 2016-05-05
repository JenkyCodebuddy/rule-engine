package jenky.codebuddy;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @Autowired
    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();
    public String json;
    //test

    @RequestMapping(method = RequestMethod.POST)
    public ScoreModel createScoreFromMetrics() {
        httpRequest http = new httpRequest();
        try{
            json = http.sendPost();
        }
        catch (Exception e ){
            System.out.println("Oops!");
        }
        System.out.println(json);
        return new ScoreModel(12);
        //return processMetrics.getScoreModel();
    }


    @RequestMapping(method = RequestMethod.GET)
    public ScoreModel retrieveScore() {
        String[] val = {"100", "1", "1", "1", "1", "1", "1"};
       // Calc calculator = new Calc(val);
        return new ScoreModel(12);
    }

}