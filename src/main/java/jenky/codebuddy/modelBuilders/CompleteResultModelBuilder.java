package jenky.codebuddy.modelbuilders;

import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Commit;
import jenky.codebuddy.models.rest.CompleteResult;
import wildtornado.scocalc.objects.Score;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CompleteResultModelBuilder { //class which builds rest models and supplies these to the controller

    private Score scoreModel;
    private Map committerInfoMap;
    private String sonarqubeJson;
    private Commit commitModel;
    private CompleteResult completeResultModel;

    public CompleteResultModelBuilder(String sonarqubeResponse, Map requestParams) {
        this.committerInfoMap = requestParams;
        this.sonarqubeJson = sonarqubeResponse;
    }

    public CompleteResult buildCompleteResultModel(){

        completeResultModel = new CompleteResult(buildScoreModel(sonarqubeJson), buildCommitModel(committerInfoMap));
        return completeResultModel;
    }

    private Score buildScoreModel(String json){
        ScoreModelBuilder builder = new ScoreModelBuilder(json);
        builder.createScoreModel();
        scoreModel = builder.getScoreModel();
        return scoreModel;
    }

    private Commit buildCommitModel(Map commitInfo){
        CommitModelBuilder github = new CommitModelBuilder(commitInfo);
        commitModel = github.getCommitModel();
        return commitModel;
    }


}
