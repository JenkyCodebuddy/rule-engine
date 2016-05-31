package jenky.codebuddy.database.project;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Service
public class ProjectServiceImpl extends GenericServiceImpl<Project, Integer> implements ProjectService {

    private ProjectDao projectDao;

    @Autowired
    public ProjectServiceImpl(@Qualifier("projectDaoImpl") ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public ProjectServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProject(Project project) {
        projectDao.saveProject(project);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfProjectExists(String projectName) {
        return projectDao.checkIfProjectExists(projectName);
    }
}
