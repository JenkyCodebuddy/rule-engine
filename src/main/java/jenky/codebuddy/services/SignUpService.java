package jenky.codebuddy.services;

import jenky.codebuddy.SendMail;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.database.verification.VerificationServiceImpl;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.entities.Verification;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * Created by joost on 3-6-2016.
 */
public class SignUpService {

    public SendMail sendMail;
    private ApplicationContext context;

    public SignUpService() {
        setSendMail(new SendMail());
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
    }

    public void signUpNewUser(String userEmail){
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        if(userService.checkIfUserExists(userEmail)){
            System.out.println("User exists");
            //user exists, do nothing?
        }
        else{
            String verificationCode = generateValidationCode();
            saveVerificationCode(userEmail, verificationCode);
            getSendMail().sendVerifcationMail(userEmail, verificationCode);
        }
    }

    public void checkVerificationCode(String verificationCode, String password){
        VerificationServiceImpl verificationService = (VerificationServiceImpl) getContext().getBean("verificationServiceImpl");
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        if(verificationService.checkIfVerificationExists(verificationCode)){
            Verification verification = verificationService.getVerificationIfExists(verificationCode);
            userService.setPasswordForUser(password,verification.getUser().getEmail(),new Date());
            verificationService.removeVerification(verification);
        }
        else{
            System.out.println("Wrong code!");
        }
    }

    private String generateValidationCode(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private void saveVerificationCode(String userEmail, String verificationCode){
        VerificationServiceImpl verificationService = (VerificationServiceImpl) getContext().getBean("verificationServiceImpl");
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        User user = new User();
        user.setCreated_at(new Date());
        user.setEmail(userEmail);
        userService.addUser(user);
        Verification verification = new Verification();
        verification.setCode(verificationCode);
        verification.setCreated_at(new Date());
        verification.setUser(userService.getUserIfExists(userEmail));
        verificationService.addNewVerfication(verification);
    }

    public SendMail getSendMail() {
        return sendMail;
    }

    public void setSendMail(SendMail sendMail) {
        this.sendMail = sendMail;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
