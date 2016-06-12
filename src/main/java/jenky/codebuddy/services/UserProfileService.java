package jenky.codebuddy.services;

import jenky.codebuddy.models.rest.Profile;

/**
 * Created by Fabian on 12-6-2016.
 */
public interface UserProfileService {
    /**
     * @param token
     * @return Profile
     */
    Profile returnProfile(String token);
}
