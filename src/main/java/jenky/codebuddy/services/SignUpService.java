package jenky.codebuddy.services;

import jenky.codebuddy.SendMail;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.database.verification.VerificationServiceImpl;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.entities.Verification;
import jenky.codebuddy.models.rest.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.vendor.Database;

import java.util.Date;
import java.util.Random;

/**
 * Created by joost on 3-6-2016.
 */
public class SignUpService {

    private SendMail sendMail;

    public SignUpService() {
        setSendMail(new SendMail());
    }

    public Response signUpNewUser(String userEmail){
        if(DatabaseFactory.getUserService().checkIfUserExists(userEmail)){  //check if email supplied by user already exists in the database
            return new Response(400,"Email already in use");
        }
        else{
            String verificationCode = generateVerificationCode(); //generate new verificationcode
            saveVerificationCode(userEmail, verificationCode);  //save the verification code and email in the db
            getSendMail().sendVerifcationMail(userEmail, verificationCode); //mail the verification code to the mail address supplied by the user
            return new Response(200, "Verification code sent to " + userEmail);
        }
    }

    public Response checkVerificationCode(String verificationCode, String password){
        if(DatabaseFactory.getVerificationService().checkIfVerificationExists(verificationCode)){    //check if the supplied verification code matches the verification code in the database
            Verification verification = DatabaseFactory.getVerificationService().getVerificationIfExists(verificationCode);  //get the verification code from the database
            DatabaseFactory.getUserService().setPasswordForUser(password,verification.getUser().getEmail(),new Date());  //set the password and updatedAt timestamp for the user attached to a verification code (every verification code is linked to an user)
            DatabaseFactory.getVerificationService().removeVerification(verification); //remove the verificationcode from the database (password for user is set, so its no longer needed in the database)
            return new Response(200,"Verify code is correct, new user is created");
        }
        else{
            return new Response(400,"Wrong verification code");
        }
    }

    private String generateVerificationCode(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray(); //all possible characters in the verification code
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {  //for loop to make sure the verification code contains 10 random characters
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private void saveVerificationCode(String userEmail, String verificationCode){
        User user = new User();  //create new user with createdAt timestamp and email supplied by user
        user.setCreated_at(new Date());
        user.setEmail(userEmail);
        DatabaseFactory.getUserService().save(user);  //save new user in db
        Verification verification = new Verification(); //create new verification record with: verification code, createdAt timestamp and link it to the user which was created earlier in the method
        verification.setCode(verificationCode);
        verification.setCreated_at(new Date());
        verification.setUser(DatabaseFactory.getUserService().getUserIfExists(userEmail));
        DatabaseFactory.getVerificationService().addNewVerfication(verification); //save the verification code in db
    }

    public SendMail getSendMail() {
        return sendMail;
    }

    public void setSendMail(SendMail sendMail) {
        this.sendMail = sendMail;
    }
}
