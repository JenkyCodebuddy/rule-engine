package jenky.codebuddy.services;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Profile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 4-6-2016.
 */
public class ProfileService {

    String token;
    ApplicationContext context;

    public ProfileService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
    }

    public Profile returnProfile(String token) {
        User user = getUserWithToken(token);
        List<Commit> commits = getCommitsFromUser(user);/*
        double avgScore = getAvgScoreFromUser(user);
        double totalScore = getTotalScoreFromUser(user);
        double achievementCount = getAchievementCountFromUser(user);
        double projectCount = getProjectCountFromUser(user);
        return new Profile(user, commits, avgScore, totalScore, achievementCount, projectCount);*/
        Profile p = new Profile();
        p.setCommits(commits);
        return p;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    private User getUserWithToken(String token) {
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");
        return authenticationService.getAuthenticationIfTokenExists(token).getUser();
    }

    private List<Commit> getCommitsFromUser(User user) {
        CommitServiceImpl commitService= (CommitServiceImpl) getContext().getBean("commitServiceImpl");
        List<Commit> commits = commitService.getCommitsFromUser(user.getUser_id());
        return commits;
    }
}
