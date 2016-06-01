package jenky.codebuddy;

//import jenky.codebuddy.database.ServiceFactory;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.company.CompanyServiceImpl;
import jenky.codebuddy.database.metric.MetricServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Metric;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.rest.CompleteResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by joost on 26-5-2016.
 */
public class BusinessLogicDB {

    ApplicationContext context;
    //DatabaseServiceFactory factory = new DatabaseServiceFactory();


    public BusinessLogicDB() {
        setContext(context = new ClassPathXmlApplicationContext("spring.xml"));
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public void updateMetricTable(String metric) {
        MetricServiceImpl metricService = (MetricServiceImpl) getContext().getBean("metricServiceImpl");
        if (!metricService.checkIfMetricExists(metric)){
            Metric m = new Metric();
            m.setCreated_at(new Date());
            m.setName(metric);
            metricService.saveMetric(m);
        }
    }

    public void storeCompleteResultModel(CompleteResult completeResult) {

        ProjectServiceImpl projectService = (ProjectServiceImpl) getContext().getBean("projectServiceImpl");
        CompanyServiceImpl companyService = (CompanyServiceImpl) getContext().getBean("companyServiceImpl");

        if(!projectService.checkIfProjectExists(completeResult.getCommitmodel().getProjectName())){
            Project p = new Project();
            p.setName(completeResult.getCommitmodel().getProjectName());
            p.setCreated_at(new Date());
            projectService.addProject(p);
        }
        if(!companyService.checkIfCompanyExists(completeResult.getCommitmodel().getProjectName())){
            Project p = new Project();
            p.setName(completeResult.getCommitmodel().getProjectName());
            p.setCreated_at(new Date());
            projectService.addProject(p);
        }

        Commit c = new Commit();
        c.setCreated_at(new Date());
        c.setDeleted_at(null);
        c.setUpdated_at(null);
        c.setBranch(completeResult.getCommitmodel().getBranch());
        c.setProject(new Project());
        CommitServiceImpl commitService = (CommitServiceImpl) getContext().getBean("commitServiceImpl");
        commitService.add(c);
        //c.setProject();
    }
}

