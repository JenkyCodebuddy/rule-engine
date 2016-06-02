package jenky.codebuddy.models.rest;

import wildtornado.scocalc.objects.Score;

public class CompleteResult {

    private Commit commitmodel;
    private Score scoreModel;

    public CompleteResult(Score score, Commit commitmodel){
        this.scoreModel = score;
        this.commitmodel = commitmodel;
    }

    public Score getScoreModel() {
        return scoreModel;
    }

    public void setScoreModel(Score scoreModel) {
        this.scoreModel = scoreModel;
    }

    public Commit getCommitmodel() {
        return commitmodel;
    }

    public void setCommitmodel(Commit commitmodel) {
        this.commitmodel = commitmodel;
    }
}

