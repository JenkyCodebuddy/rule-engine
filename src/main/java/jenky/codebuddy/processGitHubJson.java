package jenky.codebuddy;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jenky.codebuddy.models.restModels.AuthorModel;
import jenky.codebuddy.models.restModels.CommitModel;
import jenky.codebuddy.models.restModels.CommitterModel;
import org.json.JSONObject;

public class processGitHubJson extends callApi {

    private String jsonString;
    private CommitModel commitModel;
    private JsonObject committerInfoJsonObject;

    public static void main(String[] args) {
        processGitHubJson test = new processGitHubJson();
    }

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
        String committerUsername = apiResponseJsonObject.getAsJsonObject("committer").get("login").getAsString();

       createNewCommitterInfoJsonObject(authorInfo, committerInfo, authorUsername, committerUsername);
    }

    private void createNewCommitterInfoJsonObject(JsonObject committerInfo, JsonObject authorInfo, String authorUsername, String committerUsername) {

        JsonObject committerInfoJsonObject = new JsonObject();                 //create new JSONObject containing: authorinfo, authorusername, committerinfo and the committers username
        committerInfoJsonObject.add("author", authorInfo);
        committerInfoJsonObject.add("committer", committerInfo);
        committerInfoJsonObject.addProperty("authorUsername", authorUsername);
        committerInfoJsonObject.addProperty("committerUsername", committerUsername);

        mapJsonToCommitModel(committerInfoJsonObject);
    }

    private void mapJsonToCommitModel(JsonObject committerInfoJsonObject) {
        CommitterModel committerModel;
        AuthorModel authorModel;
        String committerUsername;
        String authorUsername;
        JsonObject committer = committerInfoJsonObject.getAsJsonObject("committer");
        JsonObject author = committerInfoJsonObject.getAsJsonObject("author");

        committerModel = new CommitterModel(committer.get("name").getAsString(),
                committer.get("email").getAsString(),
                committer.get("date").getAsString());

        authorModel = new AuthorModel(author.get("name").getAsString(),
                author.get("email").getAsString(),
                author.get("date").getAsString());

        committerUsername = committerInfoJsonObject.get("committerUsername").getAsString();
        authorUsername = committerInfoJsonObject.get("authorUsername").getAsString();

        CommitModel commitModel = new CommitModel(committerModel, authorModel, committerUsername, authorUsername);
        setCommitModel(commitModel);
    }

    public void setCommitModel(CommitModel commitModel) {
        this.commitModel = commitModel;
    }

    public CommitModel getCommitModel() {
        return commitModel;
    }

}
