package jenky.codebuddy.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.token.Verify;
import jenky.codebuddy.token.models.Token;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.security.Key;
import java.util.Base64;

/**
 * Created by joost on 2-6-2016.
 */
public class AuthenticationService { //static class for checking token if token is valid

    private AuthenticationService() {

    }

    public static boolean checkIfTokenIsValid(String token){  //method to check if the supplied token matches the token given to an user. Method is static, so no instantiation is needed everytime the method is called
        Boolean valid = false;
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) new ClassPathXmlApplicationContext("spring.xml").getBean("authenticationServiceImpl");
        if(authenticationService.checkIfTokenExists(token)){    //check if the token exists in the database
            Authentication auth = authenticationService.getAuthenticationIfTokenExists(token);  //get authentication record from the database which contains the supplied token
            String keyString = auth.getAuth_key();  //get serialized key from the authentication record
            Key key = stringToKey(keyString);   //deserialize the key string to a key object
            Verify verification = new Verify();
            if(verification.verify(auth.getToken(), key, auth.getUser().getEmail())){ //check if the token is valid using: token, key and userEmail
                valid = true;
            }
        }
        return valid;
    }

    public static Key stringToKey(String keyString) { //converts a keyString to a key so it can be used for validation
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

    public static String keyToString(Key key){ //converts a Key object to a keyString, so it can be saved in the database
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
}
