package jenky.codebuddy;

import jenky.codebuddy.dao.DatabaseService;
import jenky.codebuddy.dao.DatabaseServiceFactory;
import jenky.codebuddy.models.databaseModels.User;
import org.hibernate.Criteria;

import java.util.Optional;

/**
 * Created by joost on 24-5-2016.
 */
public class TestclassForDAO {
    public static void main(String[] args) {
        DatabaseServiceFactory factory = new DatabaseServiceFactory();
        DatabaseService userService = factory.getDatabaseService("User");
        if(userService.checkIfRecordExists("email","meme")){
        }
        else {
            System.out.println("Niks");
        }
        User a = (User) userService.getRecordIfExists("email","meme");
        Optional<User> p = Optional.ofNullable(a);
        if (p.isPresent()){
            System.out.println("u = " + p.get().getId());
        } else {
            System.out.println("nope");
        }

        //System.out.println(" checkIfRecordExists " + userService.checkIfRecordExists("email","memee"));

    }
}
