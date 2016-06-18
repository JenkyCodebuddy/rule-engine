package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.ActiveProjects;
import jenky.codebuddy.models.rest.SingleProject;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.ProjectsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * This endpoints return the projects of the user and/or the score.
 */
@RestController
@RequestMapping(value = "/projects")
public class ProjectsController {

    ProjectsService projectsService;

    public ProjectsController() {
        this.projectsService = new ProjectsService();
    }

    /**
     * @param headers contains the token of the user
     * @return projects of the user or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private ActiveProjects showActiveProjectsForUser(@RequestHeader Map<String,String> headers) {
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return projectsService.returnActiveProjectsForUser(headers.get("token"));
        }
        else{
            return new ActiveProjects(400,"Token not valid");
        }
    }

    /**
     * @param project_id
     * @param headers contains the token of the user
     * @return
     */
    @RequestMapping(value = "/{project_id}", method = RequestMethod.GET)
    private SingleProject showScoresForProject(@PathVariable int project_id, @RequestHeader Map<String, String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return projectsService.returnSingleProjectWithScores(project_id, headers.get("token"));
        }
        else{
            return new SingleProject(400,"Token is not valid");
        }
    }
}
