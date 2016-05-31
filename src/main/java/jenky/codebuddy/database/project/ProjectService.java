package jenky.codebuddy.database.project;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Project;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface ProjectService extends GenericService<Project, Integer> {
    public List<Project> getAllProjects();

    public void addProject(Project project);

    public boolean checkIfProjectExists(String projectName);
}
