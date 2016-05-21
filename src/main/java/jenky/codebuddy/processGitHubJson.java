package jenky.codebuddy;


import com.google.gson.JsonObject;
import jenky.codebuddy.models.restModels.Commit;

import java.util.Map;

public class processGitHubJson {
    private Commit commitModel;

    public processGitHubJson(Map commitInfo) {
        createCommitModel(commitInfo);
    }

    public void createCommitModel(Map commitInfo) {
        commitModel = new Commit(commitInfo.get("username").toString(),
                commitInfo.get("email").toString(),
                commitInfo.get("branch").toString(),
                commitInfo.get("sha").toString(),
                filterRegex(commitInfo.get("projectUrl").toString()));
        setCommitModel(commitModel);
    }

    public void setCommitModel(Commit commitModel) {
        this.commitModel = commitModel;
    }

    public Commit getCommitModel() {
        return commitModel;
    }

    private String filterRegex(String url) {
        String[] paths = url.split("/");
        return paths[2];
    }

}
