package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Profile;

import java.util.List;

/**
 * Created by joost on 4-6-2016.
 */
public class ProfileService {

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
}
