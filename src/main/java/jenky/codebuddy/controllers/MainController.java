package jenky.codebuddy.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.BusinessLogicController;
import jenky.codebuddy.BusinessLogicDB;
import jenky.codebuddy.modelbuilders.CommitModelBuilder;
import jenky.codebuddy.modelbuilders.ScoreModelBuilder;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.*;
import jenky.codebuddy.services.*;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

//import jenky.codebuddy.models.rest.Mail;
//import jenky.codebuddy.signUpMail;

@RestController //all requests to the "/score" endpoint test
public class MainController {

    String sonarqubeResponse;
    Map<String,String> githubInfoMap;
    private boolean isVerfied;
    private CompleteResult result;

    AchievementsService achievementsService;
    AuthenticationService authenticationService;
    LoginService loginService;
    ProfileService profileService;
    ShopService shopService;
    SignUpService signUpService;

    public MainController(){
        setAchievementsService(new AchievementsService());
        setLoginService(new LoginService());
        setProfileService(new ProfileService());
        setShopService(new ShopService());
        setSignUpService(new SignUpService());
    }

    @RequestMapping(value = "/achievements", method = RequestMethod.GET)
    private Achievements getAllAchievements(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.getAllAchievements();
        }
        else{
            return new Achievements("Token not valid");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST) //
    private Response login(@RequestHeader Map<String,String> headers) {
        return loginService.login(headers.get("email"), headers.get("password"));
    }


    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    private Profile getProfile(@RequestHeader Map<String,String> headers) {
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return profileService.returnProfile(headers.get("token"));
        }
        else{
            return new Profile("Token not valid");
        }
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    private void saveScore(@RequestHeader Map<String, String> headers){
        ScoreUserService scoreUserService = new ScoreUserService();
        Gson gson = new Gson();
        String sonarqubeResponse = headers.get("sonarquberesponse");
        Type test = new TypeToken<List<SonarResponse>>(){}.getType();
        List<SonarResponse> sonarResponseList = gson.fromJson(sonarqubeResponse, test);
        SonarResponse sonarResponse = sonarResponseList.get(0);
        githubInfoMap = scoreUserService.createGithubUserInfoMap(headers);
        CommitModelBuilder commitModelBuilder = new CommitModelBuilder(githubInfoMap);

        ScoreModelBuilder scoreModelBuilder = new ScoreModelBuilder(sonarResponse, commitModelBuilder.getUserCommitModel());
        scoreUserService = new ScoreUserService(scoreModelBuilder.getScoreModel(), sonarResponse, commitModelBuilder.getUserCommitModel());
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    private Items getAllItems(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getShopService().getAllItems();
        }
        else{
            return new Items("Token not valid");
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public @ResponseBody
    Response signUpNewUser(@RequestHeader Map<String,String> headers){ //sign up a new user with an email supplied by user
        return getSignUpService().signUpNewUser(headers.get("email"));
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public @ResponseBody Response checkVerificationCode(@RequestHeader Map<String, String> headers){
        return getSignUpService().checkVerificationCode(headers.get("verificationcode"), headers.get("password"));  //check verification code supplied by user, and if correct set password
    }



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    private String test(){
        return "WE BACK ONLINE BOIS";
    }

    private void setCompleteResultModel(CompleteResult result){
        this.result = result;
    }

    public AchievementsService getAchievementsService() {
        return achievementsService;
    }

    public void setAchievementsService(AchievementsService achievementsService) {
        this.achievementsService = achievementsService;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }


    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    public SignUpService getSignUpService() {
        return signUpService;
    }

    public void setSignUpService(SignUpService signUpService) {
        this.signUpService = signUpService;
    }


    //all requests to the "/score" endpoint.

}