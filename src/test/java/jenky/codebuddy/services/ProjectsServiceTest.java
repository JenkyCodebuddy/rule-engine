package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.ActiveProjects;
import jenky.codebuddy.models.rest.SingleProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by joost on 12-6-2016.
 */
public class ProjectsServiceTest {

    private static ProjectsService userProjectsService;
    private static ActiveProjects activeProjects;
    private static SingleProject singleProject;
    private static Project project1;
    private static Project project2;
    private static Project project3;
    private static Score score1;
    private static Score score2;
    private static User user;
    private static String token;

    @BeforeClass
    public static void setup(){
        userProjectsService = Mockito.mock(ProjectsService.class);
        project1 = new Project(null, null, "ICTLAB PROJECT", new Date(), new Date(), new Date());
        project2 = new Project(null, null, "HR PROJECT", new Date(), new Date(), new Date());
        project3 = new Project(null, null, "TEST", new Date(), new Date(), new Date());
        user = new User("testemail@hotmail.com", "ditiseenpassword", new Date(), new Date(), new Date(), 10000, null, null, null, null, null);
        score1 = new Score(user,null,null,420,100);
        score2 = new Score(user,null,null, 6000,1000);
        activeProjects = new ActiveProjects(Arrays.asList(project1,project2,project3),200);
        singleProject = new SingleProject(Arrays.asList(score1,score2),200);
        token = "TEST_TOKEN";
        Mockito.when(userProjectsService.returnActiveProjectsForUser(token)).thenReturn(activeProjects);
        Mockito.when(userProjectsService.returnSingleProjectWithScores(49)).thenReturn(singleProject);
    }

    @Test
    public void testGetActiveProjects(){
        ActiveProjects activeProjects = userProjectsService.returnActiveProjectsForUser("TEST_TOKEN");
        Assert.assertNotNull(activeProjects);
        List<Project> allActiveProjects = activeProjects.getActiveProjects();
        Assert.assertEquals(allActiveProjects.get(0).getName(),"ICTLAB PROJECT");
        Assert.assertEquals(allActiveProjects.get(1).getName(),"HR PROJECT");
        Assert.assertEquals(allActiveProjects.get(2).getName(),"TEST");
    }

    @Test
    public void testGetSingleProject(){
        SingleProject singleProject = userProjectsService.returnSingleProjectWithScores(49);
        List<Object> scores = singleProject.getUserScores();
        Assert.assertNotNull(scores);
        Score score1 = (Score) scores.get(0);
        Score score2 = (Score) scores.get(1);
        Assert.assertEquals(100,score1.getScore(),0);
        Assert.assertEquals(6000,score2.getSonar_value(),0);
        Assert.assertEquals("testemail@hotmail.com",score1.getUser().getEmail());
        Assert.assertEquals("ditiseenpassword",score2.getUser().getPassword());
        Assert.assertEquals(10000,score2.getUser().getJenkycoins());
    }


}
