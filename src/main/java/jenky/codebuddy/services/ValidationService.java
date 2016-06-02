package jenky.codebuddy.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.token.Verification;
import jenky.codebuddy.token.models.Token;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.security.Key;
import java.util.Base64;

/**
 * Created by joost on 2-6-2016.
 */
public class ValidationService {

    ApplicationContext context;

    public ValidationService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
    }

    public boolean checkIfValid(String token){
        Boolean valid = false;
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
            }
        }
        return valid;
    }

    public Key stringToKey(String keyString) {
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

    public String keyToString(Key key){
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

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
