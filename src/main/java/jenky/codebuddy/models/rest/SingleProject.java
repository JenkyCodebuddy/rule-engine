package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.User;

import java.util.List;

public class SingleProject {

    private List<User> users;
    private int responseCode;
    private String responseMessage;

    public SingleProject() {
    }

    public SingleProject(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public SingleProject(List<User> users, int responseCode) {
        this.users = users;
        this.responseCode = responseCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

