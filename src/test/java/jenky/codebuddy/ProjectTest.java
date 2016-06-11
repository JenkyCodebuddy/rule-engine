package jenky.codebuddy;

import jenky.codebuddy.services.DatabaseFactory;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Fabian on 10-6-2016.
 */
public class ProjectTest {

    @Test
    @Transactional
    public void checkIfProjectExists() throws Exception{
        assertNotNull(DatabaseFactory.getProjectService().checkIfProjectExists(TestInfo.TESTPROJECT));
    }

    @Test
    @Transactional
    public void getAllProjects() throws Exception{
        assertNotNull(DatabaseFactory.getProjectService().getAllProjects());
    }
}
