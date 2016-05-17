package jenky.codebuddy;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jenky.codebuddy.models.restModels.AuthorModel;
import jenky.codebuddy.models.restModels.CommitModel;
import jenky.codebuddy.models.restModels.CommitterModel;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class processGitHubJson extends callApi {

    private String jsonString;
    private CommitModel commitModel;
    private JsonObject committerInfoJsonObject;

    public processGitHubJson() {
        super("https://api.github.com/repos/JenkyCodebuddy/rule-engine/commits/master", "CodebuddyTest", "dekatkrabtdekrullenvandetrap", "GET");
        this.jsonString = super.getResponse();
        readGitHubJsonString(jsonString);
    }

    private void readGitHubJsonString(String jsonString) {

        JsonObject apiResponseJsonObject = (new JsonParser()).parse(jsonString).getAsJsonObject();
        JsonObject commit = apiResponseJsonObject.getAsJsonObject("commit");
        JsonObject authorInfo = commit.getAsJsonObject("author");
        JsonObject committerInfo = commit.getAsJsonObject("committer");

        String authorUsername = apiResponseJsonObject.getAsJsonObject("author").get("login").getAsString();
        String committerUsername = apiResponseJsonObject.getAsJsonObject("author").get("login").getAsString();

        String commitID = apiResponseJsonObject.get("sha").getAsString();
        String projectName = filterRegex(apiResponseJsonObject.get("url").getAsString());

       createNewCommitterInfoJsonObject(authorInfo, committerInfo, authorUsername, committerUsername, commitID, projectName);
    }

    private void createNewCommitterInfoJsonObject(JsonObject committerInfo, JsonObject authorInfo, String committerUsername, String authorUsername, String commitID, String projectName) {

        JsonObject committerInfoJsonObject = new JsonObject();                 //create new JSONObject containing: authorinfo, authorusername, committerinfo and the committers username
        authorInfo.addProperty("username", authorUsername);
        committerInfo.addProperty("username", committerUsername);
        committerInfoJsonObject.add("author", authorInfo);
        committerInfoJsonObject.add("committer", committerInfo);
        committerInfoJsonObject.addProperty("commitID", commitID);
        committerInfoJsonObject.addProperty("projectName", projectName);

        mapJsonToCommitModel(committerInfoJsonObject);
    }

    private void mapJsonToCommitModel(JsonObject committerInfoJsonObject) {
        CommitterModel committerModel;
        AuthorModel authorModel;
        String commitID;
        String projectName;

        authorModel = buildAuthorModel(committerInfoJsonObject.getAsJsonObject("author"),
                committerInfoJsonObject.getAsJsonObject("author").get("username").getAsString());
        committerModel = buildCommitterModel(committerInfoJsonObject.getAsJsonObject("committer"),
                committerInfoJsonObject.getAsJsonObject("committer").get("username").getAsString());

        projectName = committerInfoJsonObject.get("projectName").getAsString();
        commitID = committerInfoJsonObject.get("commitID").getAsString();

        CommitModel commitModel = new CommitModel(committerModel, authorModel, commitID, projectName);
        setCommitModel(commitModel);
    }

    private AuthorModel buildAuthorModel(JsonObject author, String username){
        AuthorModel authorModel = new AuthorModel(author.get("name").getAsString(),
                author.get("email").getAsString(),
                author.get("date").getAsString(),
                username);
        return authorModel;
    }

    private CommitterModel buildCommitterModel(JsonObject committer, String username){
        CommitterModel committerModel = new CommitterModel(committer.get("name").getAsString(),
                committer.get("email").getAsString(),
                committer.get("date").getAsString(),
                username);
        return committerModel;
    }

    public void setCommitModel(CommitModel commitModel) {
        this.commitModel = commitModel;
    }

    public CommitModel getCommitModel() {
        return commitModel;
    }

    private String filterRegex(String url){
        String[] paths = url.split("/");
        return paths[5];
    }

}
