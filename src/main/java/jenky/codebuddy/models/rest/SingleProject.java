package jenky.codebuddy.models.rest;

import java.util.List;

public class SingleProject {

    private List<Profile> profile;
    private int responseCode;
    private String responseMessage;

    public SingleProject() {
    }

    public SingleProject(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public SingleProject(List<Profile> profile, int responseCode) {
        this.profile = profile;
        this.responseCode = responseCode;
    }


    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

