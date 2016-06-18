package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.ActiveProjects;
import jenky.codebuddy.models.rest.Profile;
import jenky.codebuddy.models.rest.SingleProject;

import java.util.List;

/**
 * Created by joost on 9-6-2016.
 */
public class ProjectsService {


    public ProjectsService() {
    }

    /**
     * @param token
     * @return ActiveProjects
     */
    public ActiveProjects returnActiveProjectsForUser(String token) {
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        List<Project> allProjects = DatabaseFactory.getProjectService().getActiveProjectsFromUser(user.getUser_id());
        return new ActiveProjects(allProjects,200);
    }

    /**
     * @param projectId
     * @return Singleproject
     */
    public SingleProject returnSingleProjectWithScores(int projectId, String token) {
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        if(DatabaseFactory.getProjectService().checkIfProjectExists(projectId)) {
            List<Profile> projectScores = DatabaseFactory.getScoreService().getScoresFromProject(projectId,user.getUser_id());
            return new SingleProject(projectScores,200);
        }
        else{
            return new SingleProject(400,"Project not found");
        }
    }
}
