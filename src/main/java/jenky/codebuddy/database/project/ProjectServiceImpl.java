package jenky.codebuddy.database.project;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer of Profile. Inherits the GenericService and implements the ProjectService interface.
 */
@Service
public class ProjectServiceImpl extends GenericServiceImpl<Project, Integer> implements ProjectService {

    private ProjectDao projectDao;

    /**
     * Injected by Spring
     * @param projectDao
     */
    @Autowired
    public ProjectServiceImpl(@Qualifier("projectDaoImpl") ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public ProjectServiceImpl(){

    }

    /**
     * Asks the projectDao to return all the projects.
     * Transaction management done by Spring.
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    /**
     * Asks the projectDao to save the given project.
     * Transaction management done by Spring.
     * @param project
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProject(Project project) {
        projectDao.saveProject(project);
    }

    /**
     * Asks the projectDao to check if the given project exists or not.
     * Transaction management done by Spring.
     * @param projectName
     * @return true or false
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfProjectExists(String projectName) {
        return projectDao.checkIfProjectExists(projectName);
    }

    /**
     * Asks the projectDao to check if the given project exists or not.
     * Transaction management done by Spring.
     * @param project_id
     * @return true or false
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfProjectExists(int project_id) {
        return projectDao.checkIfProjectExists(project_id);
    }

    /**
     * Asks the projectDao to return if the given project exists or not.
     * Transaction management done by Spring.
     * @param projectName
     * @return true or false
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Project getProjectIfExists(String projectName) {
        return projectDao.getProjectIfExists(projectName);
    }

    /**
     * @param user_id
     * @return int
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public double getProjectCountFromUser(int user_id) {
        return projectDao.getProjectCountFromUser(user_id);
    }

    /**
     *
     * @param user_id
     * @return List of projets the user is working on
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Project> getActiveProjectsFromUser(int user_id) {
        return projectDao.getActiveProjectsFromUser(user_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Project getProjectFromCommit(int commit_id) {
        return projectDao.getProjectFromCommit(commit_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProject(Project project) {
        projectDao.deleteProject(project);
    }
}
