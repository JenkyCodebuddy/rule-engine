package jenky.codebuddy.database.project;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Project;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Repository
public class ProjectDaoImpl extends GenericDaoImpl<Project, Integer> implements ProjectDao {

    @Override
    public List<Project> getAllProjects() {
        return super.findAll();
    }

    @Override
    public boolean checkIfProjectExists(String projectName) {
        String hql = "FROM Project p WHERE p.name = :project_name";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("project_name",projectName);
        Project result = (Project) query.uniqueResult();
        if(result != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void saveProject(Project project) {
        super.add(project);
    }
}
