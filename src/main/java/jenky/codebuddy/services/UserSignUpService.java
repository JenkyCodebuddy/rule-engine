package jenky.codebuddy.services;

import jenky.codebuddy.models.rest.Response;

/**
 * Created by Fabian on 12-6-2016.
 */
public interface UserSignUpService {
    /**
     *
     * @param userEmail
     * @return Reponse
     */
    Response signUpNewUser(String userEmail);

    /**
     * @param verificationCode
     * @param password
     * @return Response
     */
    Response checkVerificationCode(String verificationCode, String password);
}
