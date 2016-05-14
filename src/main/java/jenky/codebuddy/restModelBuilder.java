package jenky.codebuddy;

import jenky.codebuddy.models.restModels.CommitModel;
import jenky.codebuddy.models.restModels.CompleteResultModel;
import wildtornado.scocalc.objects.Score;

public class restModelBuilder { //class which builds rest models and supplies these to the controller

    private Score scoreModel;
    private CommitModel commitModel;
    private CompleteResultModel completeResultModel;

    public restModelBuilder() {
    }

    public CompleteResultModel buildCompleteResultModel(){
        completeResultModel = new CompleteResultModel(buildScoreModel(), buildCommitModel());
        return completeResultModel;
    }

    private Score buildScoreModel(){
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
