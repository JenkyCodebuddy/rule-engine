package jenky.codebuddy.database.authentication;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Service layer of Achievement. Inherits GenericService and implements the AuthenticationService interface
 */
@Service
public class AuthenticationServiceImpl extends GenericServiceImpl<Authentication, Integer> implements AuthenticationService {

    private AuthenticationDao authenticationDao;

    /**
     * Injected by Spring
     * @param authenticationDao
     */
    @Autowired
    public AuthenticationServiceImpl(@Qualifier("authenticationDaoImpl") AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    public AuthenticationServiceImpl(){

    }

    /**
     * @param authentication
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAuthentication(Authentication authentication) {
        authenticationDao.saveAuthentication(authentication);
    }

    /**
     * @param userId int
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfAuthenticationForUserExists(int userId) {
        return authenticationDao.checkIfAuthenticationForUserExists(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAuthentication(Authentication authentication) {
        authenticationDao.updateAuthentication(authentication);
    }

    /**
     * @param token
     * @return boolean
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfTokenExists(String token) {
        return authenticationDao.checkIfTokenExists(token);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Authentication getAuthenticationIfTokenExists(String token) {
        return authenticationDao.getAuthenticationIfTokenExists(token);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Authentication getAuthenticationIfUserExists(int user_id) {
        return authenticationDao.getAuthenticationIfUserExists(user_id);
    }
}
