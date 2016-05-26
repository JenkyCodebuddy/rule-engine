package jenky.codebuddy.database.services;

import jenky.codebuddy.models.entities.*;

/**
 * Created by joost on 24-5-2016.
 */
public class DatabaseServiceFactory {

    public GenericDatabaseService getDatabaseService(String entity){
        switch(entity){
            case "Achievement": return new GenericDatabaseService(Achievement.class);
            case "Commit": return new GenericDatabaseService(Commit.class);
            case "Company": return new GenericDatabaseService(Company.class);
            case "Item": return new GenericDatabaseService(Item.class);
            case "Metric": return new GenericDatabaseService(Metric.class);
            case "Project": return new GenericDatabaseService(Project.class);
            case "Score": return new GenericDatabaseService(Score.class);
            case "User": return new GenericDatabaseService(User.class);
            default: return null;
        }
    }

}
