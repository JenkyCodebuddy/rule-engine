package jenky.codebuddy.models;

import org.apache.catalina.User;

import java.util.Date;

/**
 * Created by joost on 13-5-2016.
 */
public class CommitModel {

    private CommitterModel committerModel;
    private AuthorModel authorModel;
    private String committerUsername;
    private String authorUsername;

    public CommitModel(CommitterModel committerModel, AuthorModel authorModel, String committerUsername, String authorUsername){
        this.committerModel = committerModel;
        this.authorModel = authorModel;
        this.committerUsername = committerUsername;
        this.authorUsername = authorUsername;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public CommitterModel getCommitterModel() {
        return committerModel;
    }

    public void setCommitModel(CommitterModel committerModel) {
        this.committerModel = committerModel;
    }

    public String getCommitterUsername() {
        return committerUsername;
    }

    public void setCommitterUsername(String committerUsername) {
        this.committerUsername = committerUsername;
    }
}
