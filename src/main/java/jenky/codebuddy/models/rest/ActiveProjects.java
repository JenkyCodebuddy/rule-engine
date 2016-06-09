package jenky.codebuddy.models.rest;


import jenky.codebuddy.models.entities.Project;

import java.util.List;

public class ActiveProjects {

    private List<Project> activeProjects;
    private String error;

    public ActiveProjects() {
    }

    public ActiveProjects(List<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

    public ActiveProjects(String error) {
        this.error = error;
    }

    public List<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(List<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
