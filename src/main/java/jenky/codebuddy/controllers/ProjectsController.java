package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.ActiveProjects;
import jenky.codebuddy.models.rest.SingleProject;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.ProjectsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/projects")
public class ProjectsController {

    ProjectsService projectsService;

    public ProjectsController() {
        setProjectsService(new ProjectsService());
    }

    @RequestMapping(method = RequestMethod.GET)
    private ActiveProjects showActiveProjectsForUser(@RequestHeader Map<String,String> headers) {
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getProjectsService().returnActiveProjectsForUser(headers.get("token"));
        }
        else{
            return new ActiveProjects(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/{project_id}", method = RequestMethod.GET)
    private SingleProject showScoresForProject(@PathVariable int project_id, @RequestHeader Map<String, String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return projectsService.returnSingleProjectWithScores(project_id);
        }
        else{
            return new SingleProject(400,"Token is not valid");
        }
    }

    public ProjectsService getProjectsService() {
        return projectsService;
    }

    public void setProjectsService(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }
}
