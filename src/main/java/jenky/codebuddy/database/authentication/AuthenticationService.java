package jenky.codebuddy.database.authentication;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Authentication;

import java.util.Date;

/**
 * Specfic methods for the service Achievement. Inherits GenericService interface
 */
public interface AuthenticationService extends GenericService<Authentication, Integer> {

    /**
     * @param authentication
     */
    public void saveAuthentication(Authentication authentication);

    /**
     * @param userId
     * @param token
     * @param key
     * @param updated_at
     */
    public void updateAuthentication(int userId, String token, String key, Date updated_at);

    /**
     * @param userId
     * @return int userId
     */
    public boolean checkIfAuthenticationForUserExists(int userId);

    /**
     * @param token
     * @return boolean
     */
    public boolean checkIfTokenExists(String token);

    /**
     * @param token
     * @return String token otherwise null
     */
    public Authentication getAuthenticationIfTokenExists(String token);
}
