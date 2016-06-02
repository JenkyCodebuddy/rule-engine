package jenky.codebuddy;

//import jenky.codebuddy.database.ServiceFactory;
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
import jenky.codebuddy.token.Verification;
import jenky.codebuddy.token.models.Token;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.security.Key;
import java.util.Base64;
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
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");
        User user;
        Token token = null;
        Authentication authentication = new Authentication();
        if(userService.checkIfUserExists(email)){
            user = userService.getUserIfExists(email);
            if (user.getPassword().equals(password)){
                token = generateToken(email);
                if(authenticationService.checkIfAuthenticationForUserExists(user.getUser_id())){
                    updateAuthentication(user.getUser_id(), token.getToken(), keyToString(token.getKey()));
                }
                else{
                    createNewAuthentication(user, token.getToken(), keyToString(token.getKey()));
                }
            }
            else{
                System.out.println("Wrong password");
            }
        }
        else{
            System.out.println("Email does not exist");
        }
        return token.getToken();
    }

    private void updateAuthentication(int userId, String token, String key){
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");
        authenticationService.updateAuthentication(userId, token, key, new Date());
        System.out.println("Updated an existing authentication record!");
    }

    private void createNewAuthentication(User user, String token, String key){
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");
        Authentication authentication = new Authentication();
        authentication.setUser(user);
        authentication.setToken(token);
        authentication.setAuth_key(key);
        authentication.setCreated_at(new Date());
        authenticationService.saveAuthentication(authentication);
        System.out.println("Created a new authentication record!");
    }

    private Token generateToken(String email){
        Token token = new Token();
        Key key = MacProvider.generateKey();
        token.setToken(Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, key).compact());
        token.setKey(key);
        token.setId(email);
        return token;
    }

    private String keyToString(Key key){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream( baos );
            oos.writeObject( key );
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String keyString = Base64.getEncoder().encodeToString(baos.toByteArray());
        return keyString;
    }

    private Key stringToKey(String keyString) {
        Key key = null;
        byte[] data = Base64.getDecoder().decode(keyString);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new ByteArrayInputStream(data));
            Object o = ois.readObject();
            key = (Key) o;
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }

    public boolean checkIfValid(String token){
        Boolean valid = null;
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");
        if(authenticationService.checkIfTokenExists(token)){
            System.out.println("Token exists in database");
            Authentication auth = authenticationService.getAuthenticationIfTokenExists(token);
            String keyString = auth.getAuth_key();
            Key key = stringToKey(keyString);
            jenky.codebuddy.token.Verification verification = new Verification();
            if(verification.verify(auth.getToken(), key, auth.getUser().getEmail())){
                System.out.println("Token matches email, token is valid");
                valid = true;
            }else{
                valid = false;
            }
        }
        return valid;
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

