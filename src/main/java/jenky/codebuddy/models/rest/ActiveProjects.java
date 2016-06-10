package jenky.codebuddy.models.rest;


import jenky.codebuddy.models.entities.Project;

import java.util.List;

public class ActiveProjects {

    private List<Project> activeProjects;
    private int responseCode;
    private String responseMessage;

    public ActiveProjects() {
    }

    public ActiveProjects(List<Project> activeProjects, int responseCode) {
        this.activeProjects = activeProjects;
        this.responseCode = responseCode;
    }

    public ActiveProjects(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public List<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(List<Project> activeProjects) {
        this.activeProjects = activeProjects;
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
