package jenky.codebuddy;

import jenky.codebuddy.database.project.ProjectServiceImpl;
import org.springframework.context.ApplicationContext;

/**
 * Created by Fabian on 10-6-2016.
 */
public class ProjectTest {

    private ApplicationContext context;
    private ProjectServiceImpl projectService;

    public ProjectTest(){

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
}
