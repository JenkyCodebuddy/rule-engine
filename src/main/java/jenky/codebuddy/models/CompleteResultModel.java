package jenky.codebuddy.models;

import wildtornado.scocalc.objects.Score;

public class CompleteResultModel {

    private CommitModel commitmodel;
    private Score scoreModel;

    public CompleteResultModel(Score score, CommitModel commitmodel){
        this.scoreModel = score;
        this.commitmodel = commitmodel;
    }

    public Score getScoreModel() {
        return scoreModel;
    }

    public void setScoreModel(Score scoreModel) {
        this.scoreModel = scoreModel;
    }

    public CommitModel getCommitmodel() {
        return commitmodel;
    }

    public void setCommitmodel(CommitModel commitmodel) {
        this.commitmodel = commitmodel;
    }
}
