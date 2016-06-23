package jenky.codebuddy.services;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.token.Verify;
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

    /**
     * @param token
     * @return boolean
     */
    public static boolean checkIfTokenIsValid(String token){  //method to check if the supplied token matches the token given to an user. Method is static, so no instantiation is needed everytime the method is called
       System.out.println("***************************Checking if toking is valid"****************************);
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

    /**
     * @param keyString
     * @return Key
     */
    public static Key stringToKey(String keyString) { //converts a keyString to a key so it can be used for validation
	System.out.println("***************************Coverting string to key"****************************);
        Key key = null;
        byte[] data = Base64.getDecoder().decode(keyString);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new ByteArrayInputStream(data));
            Object o = ois.readObject();
            key = (Key) o;
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return key;
    }

    /**
     * @param key
     * @return String
     */
    public static String keyToString(Key key){ //converts a Key object to a keyString, so it can be saved in the database
	System.out.println("***************************Converting key to string"****************************);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream( baos );
            oos.writeObject( key );
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
