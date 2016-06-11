package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.ActiveProjects;
import jenky.codebuddy.models.rest.SingleProject;

import java.util.List;

/**
 * Created by joost on 9-6-2016.
 */
public class ProjectsService {

    public ProjectsService() {
    }

    public ActiveProjects returnActiveProjectsForUser(String token) {
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        List<Project> allProjects = DatabaseFactory.getProjectService().getActiveProjectsFromUser(user.getUser_id());
        return new ActiveProjects(allProjects,200);
    }

    public SingleProject returnSingleProjectWithScores(int projectId) {
        if(DatabaseFactory.getProjectService().checkIfProjectExists(projectId)) {
            List<Object> projectScores = DatabaseFactory.getScoreService().getScoresFromProject(projectId);
            return new SingleProject(projectScores,200);
        }
        else{
            return new SingleProject(400,"Project not found");
        }
    }
}
