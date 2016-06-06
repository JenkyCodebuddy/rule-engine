package jenky.codebuddy.database.project;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Project;

import java.util.List;

/**
 * Defines the specific methods ProjectDao must have.
 */
public interface ProjectDao extends GenericDao<Project, Integer> {
    public List<Project> getAllProjects();

    public void saveProject(Project project);

    public boolean checkIfProjectExists(String projectName);

    public Project getProjectIfExists(String projectname);
}
