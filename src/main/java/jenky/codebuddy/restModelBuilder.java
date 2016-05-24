package jenky.codebuddy;

import com.sun.security.auth.UserPrincipal;
import jenky.codebuddy.models.databaseModels.Achievement;
import jenky.codebuddy.models.databaseModels.User;
import jenky.codebuddy.models.restModels.Commit;
import jenky.codebuddy.models.restModels.CompleteResult;
import org.hibernate.Hibernate;
import wildtornado.scocalc.objects.Score;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class restModelBuilder { //class which builds rest models and supplies these to the controller

    private Score scoreModel;
    private Map committerInfoMap;
    private String sonarqubeJson;
    private Commit commitModel;
    private CompleteResult completeResultModel;

    public restModelBuilder(String sonarqubeResponse, Map requestParams) {
        this.committerInfoMap = requestParams;
        this.sonarqubeJson = sonarqubeResponse;
    }

    public CompleteResult buildCompleteResultModel(){

        completeResultModel = new CompleteResult(buildScoreModel(sonarqubeJson), buildCommitModel(committerInfoMap));
        persist(completeResultModel);
        return completeResultModel;
    }

    private Score buildScoreModel(String json){
        processSonarqubeJson process = new processSonarqubeJson(json);
        process.createScoreModel();
        scoreModel = process.getScoreModel();
        return scoreModel;
    }

    private Commit buildCommitModel(Map commitInfo){
        processGitHubJson github = new processGitHubJson(commitInfo);
        commitModel = github.getCommitModel();
        return commitModel;
    }

    public void persist(CompleteResult completeResultModel){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Set<jenky.codebuddy.models.databaseModels.Score> scores = new HashSet<jenky.codebuddy.models.databaseModels.Score>();
        User user = new User(1,completeResultModel.getCommitmodel().getUsername(),completeResultModel.getCommitmodel().getEmail(),new Date(),new Date(),new Date(),123,scores);
        Achievement achievement = new Achievement();
        System.out.println("Test");
 //       Commit commit = new Commit();


       // HibernateClass test = new HibernateClass(user);
    }

}
