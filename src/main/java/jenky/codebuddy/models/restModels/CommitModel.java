package jenky.codebuddy.models.restModels;

public class CommitModel {

    private CommitterModel committerModel;
    private AuthorModel authorModel;
    private String commitID;
    private String projectName;

    public CommitModel(CommitterModel committerModel, AuthorModel authorModel, String commitID, String projectName){
        this.committerModel = committerModel;
        this.authorModel = authorModel;
        this.projectName = projectName;
        this.commitID = commitID;
    }

    public CommitterModel getCommitterModel() {
        return committerModel;
    }

    public void setCommitterModel(CommitterModel committerModel) {
        this.committerModel = committerModel;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public String getCommitID() {
        return commitID;
    }

    public void setCommitID(String commitID) {
        this.commitID = commitID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
