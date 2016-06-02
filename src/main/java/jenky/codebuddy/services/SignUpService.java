package jenky.codebuddy.services;

import jenky.codebuddy.SendMail;
import jenky.codebuddy.database.user.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    public void signUpNewUser(String userEmail, String password){
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        if(userService.checkIfUserExists(userEmail)){
            System.out.println("User exists");
            //user exists, do nothing?
        }
        else{
            getSendMail().sendVerifcationMail(userEmail, generateValidationCode());
        }
    }

    private String generateValidationCode(){
        return "testVerificationCode";
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
