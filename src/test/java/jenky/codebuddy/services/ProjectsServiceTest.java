package jenky.codebuddy.services;

import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.SingleProject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joost on 20-8-2016.
 */
public class ProjectsServiceTest {
    private User user;
    private ProjectsService projectsService;
    @Before
    public void setUp(){
        user = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
        projectsService = new ProjectsService();
    }
    @Test
    public void returnActiveProjectsForUser() throws Exception {
        assertNotNull(projectsService.returnActiveProjectsForUser(user.getAuthentication().getToken()));
    }

    @Test
    public void returnSingleProjectWithScores() throws Exception {
        SingleProject singleProject = projectsService.returnSingleProjectWithScores(DatabaseFactory.getProjectService().getActiveProjectsFromUser(user.getUser_id()).iterator().next().getId(), user.getAuthentication().getToken());
        assertNotNull(singleProject);
        assertFalse(singleProject.getProfile().isEmpty());
        assertEquals(200,singleProject.getResponseCode());
    }
}