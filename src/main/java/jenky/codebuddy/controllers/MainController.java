package jenky.codebuddy.controllers;

import jenky.codebuddy.BusinessLogicController;
import jenky.codebuddy.BusinessLogicDB;
import jenky.codebuddy.models.rest.CompleteResult;
import jenky.codebuddy.modelbuilders.CompleteResultModelBuilder;
//import jenky.codebuddy.models.rest.Mail;
//import jenky.codebuddy.signUpMail;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@RestController //all requests to the "/score" endpoint test
public class MainController {

    String sonarqubeResponse;
    Map<String,String> githubInfoMap;
    private CompleteResult result;
    private BusinessLogicController businessLogicController;
    private BusinessLogicDB businessLogicDB;

    public MainController(){
        setBusinessLogicDB(new BusinessLogicDB());
        setBusinessLogicController(new BusinessLogicController());
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public String createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        sonarqubeResponse = headers.get("sonarquberesponse");
        return sonarqubeResponse;
        //githubInfoMap = getBusinessLogicController().createGithubUserInfoMap(headers);
        //setCompleteResultModel(new CompleteResultModelBuilder(sonarqubeResponse, githubInfoMap).buildCompleteResultModel());
        //getBusinessLogicDB().storeCompleteResultModel(getCompleteResultModel());
    }

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

}