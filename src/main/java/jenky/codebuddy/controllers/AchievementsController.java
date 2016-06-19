package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Achievements;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.AchievementsService;
import jenky.codebuddy.services.AuthenticationService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * This endpoints return the achievements that the user has.
 */
@RestController
@RequestMapping(value = "/achievements")
public class AchievementsController {

    AchievementsService achievementsService;

    public AchievementsController() {
        this.achievementsService = new AchievementsService();
    }
    /**
     * @param headers Contains the token of the user.
     * @return achievements or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Achievements getAllAchievements(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.returnAchievements(headers.get("token"));
        }
        else{
            return new Achievements(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/10_commit_achievement", method = RequestMethod.POST)
    private Response tenCommitAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.tenCommitAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/100_commit_achievement", method = RequestMethod.POST)
    private Response hundredCommitAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.hundredCommitAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/1000_commit_achievement", method = RequestMethod.POST)
    private Response thousandCommitAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.thousandCommitAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/saving_up_achievement", method = RequestMethod.POST)
    private Response savingUpAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.savingUpAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/money_bank_achievement", method = RequestMethod.POST)
    private Response moneyBankAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.moneyBankAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/rich_boi_achievement", method = RequestMethod.POST)
    private Response richBoiAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.richBoiAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/first_project_achievement", method = RequestMethod.POST)
    private Response yourFirstProjectAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.yourFirstProjectAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/busy_body_achievement", method = RequestMethod.POST)
    private Response busyBodyAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.busyBodyAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/project_freak_achievement", method = RequestMethod.POST)
    private Response projectFreakProjectAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.projectFreakProjectAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/high_score_achievement", method = RequestMethod.POST)
    private Response highScoreAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.highScoreAchievement(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/x_commit_achievement", method = RequestMethod.POST)
    private Response xCommitAchievement(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.xCommitAchievement(headers.get("token"), 1, 1);
        }
        else{
            return new Response(400,"Token not valid");
        }
    }
}
