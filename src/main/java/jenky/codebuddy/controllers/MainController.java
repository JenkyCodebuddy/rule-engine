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

    //TODO if statement so that only after the user is authenticated this can be accessed
    @RequestMapping(value = "/score", method = RequestMethod.GET)
    private CompleteResult getCompleteResultModel(){
        return result;
    }

    //TODO combine this with login
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    private String tokenGenerator(@RequestHeader String id) {
        Key key = MacProvider.generateKey();
        token.setToken(Jwts.builder().setSubject(id).signWith(SignatureAlgorithm.HS512, key).compact());
        token.setKey(key);
        token.setId(id);
        return token.getToken();
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    private void authorize(@RequestHeader String userToken) {
        Verification.verify(userToken, token.getKey(), token.getId());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private void login(@RequestHeader String userToken) {
        //TODO implement login adds user to db with current token, id and key
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    private void logout(@RequestHeader String userToken) {
        //TODO implement logout removes user from db
    }

    private void setCompleteResultModel(CompleteResult result){
        this.result = result;
    }


    //all requests to the "/score" endpoint

}