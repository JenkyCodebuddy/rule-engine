package jenky.codebuddy.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.token.models.Token;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.Key;
import java.util.Date;

/**
 * Created by joost on 2-6-2016.
 */
public class LoginService {

    private ApplicationContext context;

    public LoginService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
    }

    public Response login(String email, String password) {   //method for logging an user in based on email and password
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");
        User user;
        Token token;
        if (userService.checkIfUserExists(email)) {   //check if an user exists with the supplied email in the db
            user = userService.getUserIfExists(email);  //get the user record with the supplied email from the db
            if (user.getPassword() != null) {
                if (user.getPassword().equals(password)) {
                    token = generateToken(email);   //generate a token with the email as identifier (identifier is needed for validation)
                    if (authenticationService.checkIfAuthenticationForUserExists(user.getUser_id())) {    //check if the user alread has a token
                        updateAuthentication(user.getUser_id(), token.getToken(), AuthenticationService.keyToString(token.getKey()));   //if user has token, update the user's token in the database with a new one
                    } else {
                        createNewAuthentication(user, token.getToken(), AuthenticationService.keyToString(token.getKey())); //if user doesn't have a token, create one
                    }
                    return new Response(200, token.getToken());   //return appropriate response models to the controller
                    //check if the password matches the password in the db
                } else {
                    return new Response(400, "Incorrect username or password");
                }
            } else {
                return new Response(400, "User not verified");
            }

        } else {
            return new Response(400, "Email does not exist");
        }
    }


    private Token generateToken(String email) { //method for generating a token
        Token token = new Token();
        Key key = MacProvider.generateKey();
        token.setToken(Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, key).compact()); //generate a token with: userEmail as identifier, a key
        token.setKey(key);
        token.setId(email);
        return token;
    }

    private void updateAuthentication(int userId, String token, String key) { //method for updating a record in the authentication table
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");
        authenticationService.updateAuthentication(userId, token, key, new Date());
        System.out.println("Updated an existing authentication record!");
    }

    private void createNewAuthentication(User user, String token, String key) { //method for creating a new record in the authentication table
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
