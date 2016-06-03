package jenky.codebuddy;

//import jenky.codebuddy.database.ServiceFactory;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jenky.codebuddy.database.authentication.AuthenticationService;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.company.CompanyServiceImpl;
import jenky.codebuddy.database.metric.MetricServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.*;
import jenky.codebuddy.models.rest.CompleteResult;
import jenky.codebuddy.models.rest.Profile;
import jenky.codebuddy.token.*;
import jenky.codebuddy.token.models.Token;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


public class BusinessLogicDB {

    
    ApplicationContext context;
    TokenGenerator tokenGenerator;
    //DatabaseServiceFactory factory = new DatabaseServiceFactory();


    public BusinessLogicDB() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
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

