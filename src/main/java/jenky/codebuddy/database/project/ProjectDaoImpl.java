package jenky.codebuddy.database.project;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Project;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Perisstence of ProjectDao. Inherits GenericDaoImpl and implements ProjectDao interface
 */
@Repository
public class ProjectDaoImpl extends GenericDaoImpl<Project, Integer> implements ProjectDao {

    /**
     * Get all the projects
     * @return List containing all the projects.
     */
    @Override
    public List<Project> getAllProjects() {
        return super.findAll();
    }

    /**
     * Check if the given project exists or not.
     * @param projectName
     * @return true or false
     */
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
    public Project getProjectIfExists(String projectName) {
        String hql = "FROM Project p WHERE p.name = :project_name";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("project_name",projectName);
        Project result = (Project) query.uniqueResult();
        if(result != null){
            return result;
        }
        else{
            return null;
        }
    }

    /**
     * Saves the project.
     * @param project
     */
    @Override
    public void saveProject(Project project) {
        super.add(project);
    }

    @Override
    public double getProjectCountFromUser(int user_id) {
        String hql = "SELECT COUNT(DISTINCT project.id) FROM Project project INNER JOIN project.commits as commits INNER JOIN commits.scores as scores WHERE scores.user =:user_id GROUP BY scores.user";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        long result =  (long) query.uniqueResult();
        double d = (double) result;
        return result;
    }

    @Override
    public List<Project> getActiveProjectsFromUser(int user_id) {
        String hql = "SELECT project.name FROM Project project INNER JOIN project.commits as commits INNER JOIN commits.scores as scores WHERE scores.user =:user_id GROUP BY project.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        List<Project> result = query.list();
        return result;
    }
}
