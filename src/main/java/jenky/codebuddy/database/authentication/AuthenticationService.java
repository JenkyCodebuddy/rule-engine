package jenky.codebuddy.database.authentication;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Authentication;

import java.util.Date;

/**
 * Specfic methods for the service Achievement. Inherits GenericService interface
 */
public interface AuthenticationService extends GenericService<Authentication> {
    public void saveAuthentication(Authentication authentication);

    public void updateAuthentication(int userId, String token, String key, Date updated_at);

    public boolean checkIfAuthenticationForUserExists(int userId);

    public boolean checkIfTokenExists(String token);

    public Authentication getAuthenticationIfTokenExists(String token);
}
