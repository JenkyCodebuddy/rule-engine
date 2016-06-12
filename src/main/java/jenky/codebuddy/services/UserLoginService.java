package jenky.codebuddy.services;

import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.token.models.Token;

/**
 * Created by Fabian on 12-6-2016.
 */
public interface UserLoginService {
    /**
     * @param email
     * @param password
     * @return Reponse
     */
    Response login(String email, String password);

    /**
     * @param email
     * @return Token
     */
    Token generateToken(String email);
}
