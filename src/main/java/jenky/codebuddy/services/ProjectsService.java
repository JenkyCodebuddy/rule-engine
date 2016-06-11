package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.Score;
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

    public SingleProject returnSingleProjectWithScores(int project_id) {
        if(DatabaseFactory.getProjectService().checkIfProjectExists(project_id)) {
            List<Object> projectScores = DatabaseFactory.getScoreService().getScoresFromProject(project_id);
            return new SingleProject(projectScores,200);
        }
        else{
            return new SingleProject(400,"Project not found");
        }
    }
}
