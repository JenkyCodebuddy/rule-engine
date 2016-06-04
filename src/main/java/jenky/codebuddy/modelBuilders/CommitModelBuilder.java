package jenky.codebuddy.modelbuilders;


import jenky.codebuddy.models.rest.UserCommit;

import java.util.Map;

public class CommitModelBuilder {
    private UserCommit userCommitModel;

    public CommitModelBuilder(Map commitInfo) {
        createCommitModel(commitInfo);
    }

    public void createCommitModel(Map commitInfo) {
        userCommitModel = new UserCommit(commitInfo.get("username").toString(),
                commitInfo.get("email").toString(),
                commitInfo.get("branch").toString(),
                commitInfo.get("sha").toString(),
                filterRegex(commitInfo.get("projectName").toString()));
        setUserCommitModel(userCommitModel);
    }

    public void setUserCommitModel(UserCommit userCommitModel) {
        this.userCommitModel = userCommitModel;
    }

    public UserCommit getUserCommitModel() {
        return userCommitModel;
    }

    private String filterRegex(String url) {
        String[] paths = url.split("/");
        return paths[2];
    }

}
