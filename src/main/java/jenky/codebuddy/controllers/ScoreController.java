package jenky.codebuddy.controllers;

import com.google.gson.Gson;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.services.ValidationService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by joost on 3-6-2016.
 */
@RequestMapping(value = "/score")
public class ScoreController {
    @RequestMapping(method = RequestMethod.POST)
    public String createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        return "test";
    }

    @RequestMapping(method = RequestMethod.GET)
    private void returnModel(){
    }

    @RequestMapping(value = "/testdb")
    private String test(@RequestHeader Map<String, String> headers){
        Gson gson = new Gson();
        SonarResponse sonarResponse = gson.fromJson(headers.get("sonarquberesponse"), SonarResponse.class);
        return sonarResponse.toString();
    }
}
