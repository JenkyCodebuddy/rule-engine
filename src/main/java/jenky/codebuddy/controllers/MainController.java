package jenky.codebuddy.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jenky.codebuddy.BusinessLogicController;
import jenky.codebuddy.BusinessLogicDB;
import jenky.codebuddy.models.rest.CompleteResult;
import jenky.codebuddy.modelbuilders.CompleteResultModelBuilder;
import jenky.codebuddy.token.Verification;
import jenky.codebuddy.token.models.Token;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Map;

@RestController //all requests to the "/score" endpoint test
public class MainController {

    String sonarqubeResponse;
    Map<String,String> githubInfoMap;
    private CompleteResult result;
    private Token token = new Token();
    private BusinessLogicController businessLogicController = new BusinessLogicController();
    private BusinessLogicDB businessLogicDB = new BusinessLogicDB();


    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public void createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        sonarqubeResponse = headers.get("sonarquberesponse");
        githubInfoMap = businessLogicController.createGithubUserInfoMap(headers);
        setCompleteResultModel(new CompleteResultModelBuilder(sonarqubeResponse, githubInfoMap).buildCompleteResultModel());
        businessLogicDB.storeCompleteResultModel(getCompleteResultModel());
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET)
    private CompleteResult getCompleteResultModel(){
        return result;
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    private String tokenGenerator(@RequestHeader String id) {
        Key key = MacProvider.generateKey();
        token.setToken(Jwts.builder().setSubject(id).signWith(SignatureAlgorithm.HS512, key).compact());
        token.setKey(key);
        token.setId(id);
        return this.token.getToken();
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    private void authorize(@RequestHeader String userToken) {
        Verification.verify(userToken, token.getKey(), token.getId());
    }

    private void setCompleteResultModel(CompleteResult result){
        this.result = result;
    }


    //all requests to the "/score" endpoint

}