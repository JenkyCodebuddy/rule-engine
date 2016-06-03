package jenky.codebuddy.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by joost on 3-6-2016.
 */
@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @RequestMapping(method = RequestMethod.POST)
    public String createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        return "test";
    }

    @RequestMapping(method = RequestMethod.GET)
    private void returnModel(){
    }

    @RequestMapping(value = "/testdb", method = RequestMethod.POST)
    private String test(@RequestHeader Map<String, String> headers){
        Gson gson = new Gson();
        String sonarqubeResponse = headers.get("sonarquberesponse");
        Type test = new TypeToken<List<SonarResponse>>(){

        }.getType();
        List<SonarResponse> sonarResponseList = gson.fromJson(sonarqubeResponse, test);
        SonarResponse sonarResponse = sonarResponseList.get(0);
        return sonarResponse.getClass().getName();
    }
}
