package jenky.codebuddy.dao;

import jenky.codebuddy.models.databaseModels.*;

/**
 * Created by joost on 24-5-2016.
 */
public class DatabaseServiceFactory {

    public DatabaseService getDatabaseService(String entity){
        switch(entity){
            case "Achievement": return new DatabaseService(Achievement.class);
            case "Commit": return new DatabaseService(Commit.class);
            case "Company": return new DatabaseService(Company.class);
            case "Item": return new DatabaseService(Item.class);
            case "Metric": return new DatabaseService(Metric.class);
            case "Project": return new DatabaseService(Project.class);
            case "Score": return new DatabaseService(Score.class);
            case "User": return new DatabaseService(User.class);
            default: return null;
        }
    }

}
