package jenky.codebuddy;

//import jenky.codebuddy.database.ServiceFactory;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.company.CompanyServiceImpl;
import jenky.codebuddy.database.metric.MetricServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Metric;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.CompleteResult;
import jenky.codebuddy.models.rest.Profile;
import jenky.codebuddy.token.TokenGenerator;
import jenky.codebuddy.token.models.Token;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by joost on 26-5-2016.
 */
public class BusinessLogicDB {

    ApplicationContext context;
    TokenGenerator tokenGenerator;
    //DatabaseServiceFactory factory = new DatabaseServiceFactory();


    public BusinessLogicDB() {
        setContext(context = new ClassPathXmlApplicationContext("spring.xml"));
        setTokenGenerator(new TokenGenerator());
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

    public String login(String email, String password){
        User user;
        String token = null;
        UserServiceImpl u = (UserServiceImpl) getContext().getBean("userServiceImpl");
        if(u.checkIfUserExists(email)){
            user = u.getUserIfExists(email);
            if (user.getPassword().equals(password)){
                token = getTokenGenerator().createJWT("1","2","3",32L);
                System.out.println(token);

            }
            else{
                System.out.println("Wrong password");
            }
        }
        else{
            System.out.println("email does not exist");
        }
        TokenGenerator t = new TokenGenerator();
        return token;
    }

    public Profile getProfile(){
       return null;
    }

    public TokenGenerator getTokenGenerator() {
        return tokenGenerator;
    }

    public void setTokenGenerator(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }
}

