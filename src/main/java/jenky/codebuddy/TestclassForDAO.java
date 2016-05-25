package jenky.codebuddy;

import jenky.codebuddy.dao.DatabaseService;
import jenky.codebuddy.dao.DatabaseServiceFactory;
import jenky.codebuddy.models.databaseModels.Metric;
import jenky.codebuddy.models.databaseModels.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by joost on 24-5-2016.
 */
public class TestclassForDAO {
    public static void main(String[] args) {
        /*DatabaseServiceFactory factory = new DatabaseServiceFactory();
        DatabaseService userService = factory.getDatabaseService("User");
        if(userService.checkIfRecordExists("email","meme")){
        }
        else {
            System.out.println("Niks");
        }
        User p = (User) userService.getRecordIfExists("email","meme");
        System.out.println("u = " + p.getId());*/


        DatabaseService metricService = new DatabaseServiceFactory().getDatabaseService("Metric");

        ArrayList<String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("test4");
        list.add("test5");

        for(int i = 0; i<list.size(); i++){
            if(!metricService.checkIfRecordExists("name",list.get(i))){
                Metric m = new Metric();
                m.setCreated_at(new Date());
                m.setDeleted_at(new Date());
                m.setUpdated_at(new Date());
                m.setName(list.get(i));
                metricService.persist(m);
            }
        }
        //System.out.println(" checkIfRecordExists " + userService.checkIfRecordExists("email","memee"));

    }
}
