package jenky.codebuddy.database.authentication;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.Authentication;

import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * Specific methods for achievement. This interface inherits GenericDao interface
 */

public interface AuthenticationDao extends GenericDao<Authentication, Integer> {

    public void saveAuthentication(Authentication authentication);

    public void updateAuthentication(int userId, String token, String key, Date updated_at);

    public boolean checkIfAuthenticationForUserExists(int userId);

    public boolean checkIfTokenExists(String token);

    public Authentication getAuthenticationIfTokenExists(String token);

}
