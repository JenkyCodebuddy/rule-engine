package jenky.codebuddy.models.rest;

import wildtornado.scocalc.objects.Score;

public class CompleteResult {

    private UserCommit commitmodel;
    private Score scoreModel;

    public CompleteResult(Score score, UserCommit commitmodel){
        this.scoreModel = score;
        this.commitmodel = commitmodel;
    }

    public Score getScoreModel() {
        return scoreModel;
    }

    public void setScoreModel(Score scoreModel) {
        this.scoreModel = scoreModel;
    }

    public UserCommit getCommitmodel() {
        return commitmodel;
    }

    public void setCommitmodel(UserCommit commitmodel) {
        this.commitmodel = commitmodel;
    }
}

