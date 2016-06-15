package jenky.codebuddy.database.project;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.services.DatabaseFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Project> result = Optional.ofNullable((Project) query.uniqueResult());
        return result.isPresent();
    }

    /**
     * @param project_id
     * @return boolean
     */
    @Override
    public boolean checkIfProjectExists(int project_id){
        String hql = "FROM Project p WHERE p.id= :project_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("project_id", project_id);
        Optional<Project> result = Optional.ofNullable((Project) query.uniqueResult());
        return result.isPresent();
    }

    /**
     * @param projectName
     * @return Project otherwise null
     */
    @Override
    public Project getProjectIfExists(String projectName) {
        String hql = "FROM Project p WHERE p.name = :project_name";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("project_name",projectName);
        Optional<Project> result = Optional.ofNullable((Project) query.uniqueResult());
        return result.get();
    }

    /**
     * Saves the project.
     * @param project
     */
    @Override
    public void saveProject(Project project) {
        super.add(project);
    }

    /**
     * @param user_id
     * @return int
     */
    @Override
    public double getProjectCountFromUser(int user_id) {
        String hql = "SELECT COUNT(DISTINCT project.id) FROM Project project INNER JOIN project.commits as commits INNER JOIN commits.scores as scores WHERE scores.user =:user_id GROUP BY scores.user";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        long result =  (long) query.uniqueResult();
        return result;
    }

    /**
     * @param user_id
     * @return List of projects the user is working on
     */
    @Override
    public List<Project> getActiveProjectsFromUser(int user_id) {
        String hql = "FROM Project project LEFT JOIN FETCH project.commits as commits LEFT JOIN FETCH commits.scores as scores WHERE scores.user =:user_id GROUP BY project.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        List<Project> result = query.list();
        for(int i = 0; i < result.size(); i++){
            Project project = result.get(i);
            project.setUserCount(DatabaseFactory.getUserService().countUsersFromProject(project.getId()));
            result.set(i,project);
        }
        return result;
    }
}
