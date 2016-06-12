package jenky.codebuddy.database.authentication;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Authentication;

import java.util.Date;

/**
 * Specific methods for authentication. This interface inherits GenericDao interface
 */

public interface AuthenticationDao extends GenericDao<Authentication, Integer> {

    /**
     * @param authentication contains the authentication of the user
     */
    public void saveAuthentication(Authentication authentication);

    /**
     * @param userId
     * @param token
     * @param key the token was signed with
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
