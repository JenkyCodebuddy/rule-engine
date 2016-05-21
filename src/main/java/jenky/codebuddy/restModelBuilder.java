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

    public restModelBuilder(Map requestParams) {
        splitRequestParamsMap(requestParams);
    }

    public CompleteResult buildCompleteResultModel(){

        completeResultModel = new CompleteResult(buildScoreModel(sonarqubeJson), buildCommitModel(committerInfoMap));
        return completeResultModel;
    }

    private Score buildScoreModel(String sonarqubeJson){
        processSonarqubeJson process = new processSonarqubeJson(sonarqubeJson);
        scoreModel = process.getScoreModel();
        return scoreModel;
    }

    private Commit buildCommitModel(Map commitInfo){
        processGitHubJson github = new processGitHubJson(commitInfo);
        commitModel = github.getCommitModel();
        return commitModel;
    }

    private void splitRequestParamsMap(Map requestParams){ //method which splits the requestParams map and sets 2 variables
        this.sonarqubeJson = requestParams.get("sonarqubeResponse").toString();
        requestParams.remove("sonarqubeResponse");
        this.committerInfoMap = requestParams;
    }


}
