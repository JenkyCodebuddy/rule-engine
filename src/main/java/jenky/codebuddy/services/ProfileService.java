package jenky.codebuddy.services;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Profile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joost on 4-6-2016.
 */
public class ProfileService {

    String token;

    public ProfileService() {
    }

    public Profile returnProfile(String token) {
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        if(DatabaseFactory.getScoreService().checkIfUserHasScores(user.getUser_id())) {
            List<Item> items = DatabaseFactory.getItemService().getEquippedItemsFromUser(user.getUser_id());
            List<Commit> commits = DatabaseFactory.getCommitService().getCommitsFromUser(user.getUser_id());
            double avgScore = DatabaseFactory.getScoreService().getAvgScoreFromUser(user.getUser_id());
            double totalScore = DatabaseFactory.getScoreService().getTotalScoreFromUser(user.getUser_id());;
            double achievementCount = DatabaseFactory.getAchievementService().getAchievementCountFromUser(user.getUser_id());
            double projectCount = DatabaseFactory.getProjectService().getProjectCountFromUser(user.getUser_id());
            return new Profile(items, commits, totalScore, avgScore, achievementCount, projectCount, 200);
        }
        else{
            return new Profile(400, "User has no scores yet");
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
