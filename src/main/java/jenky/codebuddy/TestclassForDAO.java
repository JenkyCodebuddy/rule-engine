package jenky.codebuddy;

import jenky.codebuddy.dao.DatabaseService;
import jenky.codebuddy.dao.DatabaseServiceFactory;
import jenky.codebuddy.models.databaseModels.User;

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
        User p = (User) userService.getRecordIfExists("email","meme");
        System.out.println("u = " + p.getId());


        //System.out.println(" checkIfRecordExists " + userService.checkIfRecordExists("email","memee"));

    }
}
