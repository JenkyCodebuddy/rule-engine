package jenky.codebuddy.models.rest;


import jenky.codebuddy.models.entities.Project;

import java.util.List;

public class Projects {

    private List<Project> activeProjects;

    public Projects() {
    }

    public List<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(List<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }
}
