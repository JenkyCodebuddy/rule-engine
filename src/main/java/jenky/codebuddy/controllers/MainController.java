package jenky.codebuddy.controllers;

import jenky.codebuddy.BusinessLogicController;
import jenky.codebuddy.BusinessLogicDB;
import jenky.codebuddy.models.rest.CompleteResult;
import jenky.codebuddy.modelbuilders.CompleteResultModelBuilder;
import jenky.codebuddy.models.rest.Mail;
import jenky.codebuddy.signUpMail;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@RestController //all requests to the "/score" endpoint test
public class MainController {

    String sonarqubeResponse;
    Map<String,String> githubInfoMap;
    private CompleteResult result;
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

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    private void signUp(@RequestParam String reciepentAddress){
        signUpMail m  = new signUpMail();
        try {
            m.sendMail(reciepentAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void setCompleteResultModel(CompleteResult result){
        this.result = result;
    }

 //all requests to the "/score" endpoint

}