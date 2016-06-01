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

import javax.mail.MessagingException;
import java.security.Key;
import java.util.Map;

@RestController //all requests to the "/score" endpoint test
public class MainController {

    String sonarqubeResponse;
    Map<String,String> githubInfoMap;
    private CompleteResult result;
    //private BusinessLogicController businessLogicController;
    //private BusinessLogicDB businessLogicDB;

    public MainController(){
        setBusinessLogicDB(new BusinessLogicDB());
        setBusinessLogicController(new BusinessLogicController());
    }
    private Token token = new Token();
    private BusinessLogicController businessLogicController = new BusinessLogicController();
    private BusinessLogicDB businessLogicDB = new BusinessLogicDB();


    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public String createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        sonarqubeResponse = headers.get("sonarqubeResponse");
        return sonarqubeResponse;
        //githubInfoMap = getBusinessLogicController().createGithubUserInfoMap(headers);
        //setCompleteResultModel(new CompleteResultModelBuilder(sonarqubeResponse, githubInfoMap).buildCompleteResultModel());
        //getBusinessLogicDB().storeCompleteResultModel(getCompleteResultModel());
    }

    //TODO if statement so that only after the user is authenticated this can be accessed
    @RequestMapping(value = "/score", method = RequestMethod.GET)
    private void returnModel(){
        getCompleteResultModel();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private void login(@RequestParam String email, String password ){
       // getBusinessLogicDB().login(email,password);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    private void signUp(@RequestParam String email, String password){
        //getBusinessLogicDB().signup(email, password);
    }

   /* @RequestMapping(value = "/verify", method = RequestMethod.POST){

    }*/

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    private String test(){
        return "WE BACK ONLINE BOIS";
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


    private CompleteResult getCompleteResultModel(){
        return result;
    }

    public BusinessLogicController getBusinessLogicController() {
        return businessLogicController;
    }

    public void setBusinessLogicController(BusinessLogicController businessLogicController) {
        this.businessLogicController = businessLogicController;
    }

    public BusinessLogicDB getBusinessLogicDB() {
        return businessLogicDB;
    }

    public void setBusinessLogicDB(BusinessLogicDB businessLogicDB) {
        this.businessLogicDB = businessLogicDB;
    }

    //all requests to the "/score" endpoint

    //all requests to the "/score" endpoint

}