package jenky.codebuddy.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.token.models.Token;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.Key;
import java.util.Date;

/**
 * Created by joost on 2-6-2016.
 */
public class LoginService extends ValidationService {

    private ApplicationContext context;

    public LoginService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
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
        JSONObject json = new JSONObject();
        json.put("token", token.getToken());
        return json.toString();
    }


    private Token generateToken(String email){
        Token token = new Token();
        Key key = MacProvider.generateKey();
        token.setToken(Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, key).compact());
        token.setKey(key);
        token.setId(email);
        return token;
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

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
