package jenky.codebuddy.models.databaseModels;

import java.util.HashSet;
import java.util.Set;

public class score {

    private int id;
    private int user_id;
    private int project_id;
    private int metric_id;
    private double sonar_value;
    private int score;
    private Set<commit> commits  = new HashSet<commit>(0);
    private project project;
    private metric metric;

    public score(int id, int user_id, int project_id, int metric_id, double sonar_value, int score, Set<commit> commits, project project, metric metric) {
        this.id = id;
        this.user_id = user_id;
        this.project_id = project_id;
        this.metric_id = metric_id;
        this.sonar_value = sonar_value;
        this.score = score;
        this.commits = commits;
        this.project = project;
        this.metric = metric;
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

    public Set<commit> getCommits() {
        return commits;
    }

    public void setCommits(Set<commit> commits) {
        this.commits = commits;
    }

    public project getProject() {
        return project;
    }

    public void setProject(project project) {
        this.project = project;
    }

    public metric getMetric() {
        return metric;
    }

    public void setMetric(metric metric) {
        this.metric = metric;
    }
}
