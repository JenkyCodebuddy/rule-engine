package jenky.codebuddy.models.databaseModels;

/**
 * Created by joost on 14-5-2016.
 */
public class scoreModel {

    private int id;
    private int user_id;
    private int project_id;
    private int metric_id;
    private double sonar_value;
    private int score;

    public scoreModel(int id, int user_id, int project_id, int metric_id, double sonar_value, int score) {
        this.id = id;
        this.user_id = user_id;
        this.project_id = project_id;
        this.metric_id = metric_id;
        this.sonar_value = sonar_value;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getMetric_id() {
        return metric_id;
    }

    public void setMetric_id(int metric_id) {
        this.metric_id = metric_id;
    }

    public double getSonar_value() {
        return sonar_value;
    }

    public void setSonar_value(double sonar_value) {
        this.sonar_value = sonar_value;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
