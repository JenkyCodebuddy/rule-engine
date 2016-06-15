package jenky.codebuddy.database.project;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Project;

import java.util.List;

/**
 * This interface specifies the methods ProjectSerivce must have.
 */
public interface ProjectService extends GenericService<Project, Integer> {
    /**
     * @return List of all the projects
     */
    public List<Project> getAllProjects();

    /**
     * @param project
     */
    public void addProject(Project project);

    /**
     * @param projectName
     * @return boolean
     */
    public boolean checkIfProjectExists(String projectName);

    /**
     * @param project_id
     * @return boolean
     */
    public boolean checkIfProjectExists(int project_id);

    /**
     * @param projectname
     * @return Project otherwise null
     */
    public Project getProjectIfExists(String projectname);

    /**
     * @param user_id
     * @return int
     */
    public double getProjectCountFromUser(int user_id);

    /**
     * @param user_id
     * @return List containing the projects of the user
     */
    public List<Project> getActiveProjectsFromUser(int user_id);

    public Project getProjectFromCommit(int commit_id);

}
