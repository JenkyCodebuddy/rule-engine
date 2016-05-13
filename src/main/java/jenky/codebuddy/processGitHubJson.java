package jenky.codebuddy;


import jenky.codebuddy.models.restModels.AuthorModel;
import jenky.codebuddy.models.restModels.CommitModel;
import jenky.codebuddy.models.restModels.CommitterModel;
import org.json.JSONObject;

public class processGitHubJson extends callApi {

    private String jsonString;
    private CommitModel commitModel;
    private JSONObject committerInfoJsonObject;
    
    public processGitHubJson(){
        super("https://api.github.com/repos/JenkyCodebuddy/rule-engine/commits/master", "CodebuddyTest", "dekatkrabtdekrullenvandetrap", "GET");
        this.jsonString = super.getResponse();
    }

    public CommitModel doEverything(){
        processGitHubJson github = new processGitHubJson();
        github.readGitHubJsonString(github.jsonString);
        github.mapJsonToCommitModel();
        return github.getCommitModel();
    }

    private void readGitHubJsonString(String jsonString) {
        JSONObject apiResponseJsonObject = new JSONObject(jsonString);                  //convert json string to json object
        JSONObject commit = apiResponseJsonObject.getJSONObject("commit");              //retrieve authorinfo, commiterinfo and username info
        JSONObject authorInfo = commit.getJSONObject("author");
        JSONObject committerInfo = commit.getJSONObject("committer");
        String authorUsername = apiResponseJsonObject.getJSONObject("author").getString("login");
        String committerUsername = apiResponseJsonObject.getJSONObject("committer").getString("login");

        JSONObject result = createNewCommitterInfoJsonObject(authorInfo,committerInfo,authorUsername,committerUsername);
        setCommitterInfoJsonObject(result);
    }

    private JSONObject createNewCommitterInfoJsonObject(JSONObject committerInfo, JSONObject authorInfo, String authorUsername, String committerUsername){
        JSONObject committerInfoJsonObject = new JSONObject();                 //create new JSONObject containing: authorinfo, authorusername, committerinfo and the committers username
        committerInfoJsonObject.put("author", authorInfo);
        committerInfoJsonObject.put("committer", committerInfo);
        committerInfoJsonObject.put("authorUsername", authorUsername);
        committerInfoJsonObject.put("committerUsername", committerUsername);

        return committerInfoJsonObject;
    }

    private void mapJsonToCommitModel(){
        CommitterModel committerModel;
        AuthorModel authorModel;
        String committerUsername;
        String authorUsername;
        JSONObject committerInfoJsonObject = getCommitterInfoJsonObject();

        committerModel = new CommitterModel(committerInfoJsonObject.getJSONObject("committer").getString("name"),committerInfoJsonObject.getJSONObject("committer").getString("email"),committerInfoJsonObject.getJSONObject("committer").getString("date"));
        authorModel = new AuthorModel(committerInfoJsonObject.getJSONObject("author").getString("name"),committerInfoJsonObject.getJSONObject("author").getString("email"),committerInfoJsonObject.getJSONObject("author").getString("date"));
        committerUsername = committerInfoJsonObject.getString("committerUsername");
        authorUsername = committerInfoJsonObject.getString("authorUsername");

        CommitModel commitModel = new CommitModel(committerModel,authorModel,committerUsername,authorUsername);
        setCommitModel(commitModel);
    }

    public void setCommitModel(CommitModel commitModel) {
        this.commitModel = commitModel;
    }

    public CommitModel getCommitModel() {
        return commitModel;
    }

    public void setCommitterInfoJsonObject(JSONObject committerInfoJsonObject) {
        this.committerInfoJsonObject = committerInfoJsonObject;
    }

    public JSONObject getCommitterInfoJsonObject() {
        return committerInfoJsonObject;
    }
}
