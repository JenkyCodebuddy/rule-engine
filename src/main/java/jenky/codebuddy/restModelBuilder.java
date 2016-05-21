package jenky.codebuddy;

import jenky.codebuddy.models.restModels.CommitModel;
import jenky.codebuddy.models.restModels.CompleteResultModel;
import wildtornado.scocalc.objects.Score;

import java.util.Map;

public class restModelBuilder { //class which builds rest models and supplies these to the controller

    private Score scoreModel;
    private CommitModel commitModel;
    private CompleteResultModel completeResultModel;

    public restModelBuilder() {
    }

    public CompleteResultModel buildCompleteResultModel(Map requestParams){
        completeResultModel = new CompleteResultModel(buildScoreModel(requestParams), buildCommitModel());
        return completeResultModel;
    }

    private Score buildScoreModel(Map requestParams){
        processSonarqubeJson process = new processSonarqubeJson();
        process.createScoreModel();
        scoreModel = process.getScoreModel();
        return scoreModel;
    }

    private CommitModel buildCommitModel(){
        processGitHubJson github = new processGitHubJson();
        commitModel = github.getCommitModel();
        return commitModel;
    }
}
