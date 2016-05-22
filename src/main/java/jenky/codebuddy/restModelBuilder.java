package jenky.codebuddy;

import jenky.codebuddy.models.restModels.Commit;
import jenky.codebuddy.models.restModels.CompleteResult;
import wildtornado.scocalc.objects.Score;

import java.util.Map;

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


}
