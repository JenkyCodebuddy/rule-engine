package jenky.codebuddy.services;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.ActiveProjects;
import jenky.codebuddy.models.rest.SingleProject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 9-6-2016.
 */
public class ProjectsService {

    private ApplicationContext context;
    private ProjectServiceImpl projectService;
    private ScoreServiceImpl scoreService;
    private ItemServiceImpl itemService;
    private AuthenticationServiceImpl authenticationService;

    public ProjectsService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        setProjectService((ProjectServiceImpl) getContext().getBean("projectServiceImpl"));
        setAuthenticationService((AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl"));
        setScoreService((ScoreServiceImpl) getContext().getBean("scoreServiceImpl"));
        setItemService((ItemServiceImpl) getContext().getBean("itemServiceImpl"));
    }

    public ActiveProjects returnActiveProjectsForUser(String token) {
        User user = getUserWithToken(token);
        List<Project> allProjects = getAllProjectsFromUser(user);
        return new ActiveProjects(allProjects);
    }

    public SingleProject returnSingleProjectWithScores(int project_id) {
        List<Score> projectScores = getScoresFromProject(project_id);
        return new SingleProject(projectScores);
    }

    private User getUserWithToken(String token) {
        return getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
    }

    private List<Project> getAllProjectsFromUser(User user) {
        return getProjectService().getActiveProjectsFromUser(user.getUser_id());
    }

    private List<Score> getScoresFromProject(int project_id) {
        return getScoreService().getScoresFromProject(project_id);
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public ProjectServiceImpl getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    public AuthenticationServiceImpl getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    public ScoreServiceImpl getScoreService() {
        return scoreService;
    }

    public void setScoreService(ScoreServiceImpl scoreService) {
        this.scoreService = scoreService;
    }

    public ItemServiceImpl getItemService() {
        return itemService;
    }

    public void setItemService(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }
}
