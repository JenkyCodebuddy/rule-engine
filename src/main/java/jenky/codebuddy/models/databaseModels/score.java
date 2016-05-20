package jenky.codebuddy.models.databaseModels;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "score")
public class Score {

    @Id @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "commit_id")
    private Commit commit;

    @ManyToOne
    @JoinColumn(name = "metric_id")
    private Metric metric;

    @Column(name = "sonar_value")
    private double sonar_value;

    @Column(name = "score")
    private int score;

    public Score() {
    }

    public Score(int id, User user, Project project, Commit commit, Metric metric, double sonar_value, int score) {
        this.id = id;
        this.user = user;
        this.project = project;
        this.commit = commit;
        this.metric = metric;
        this.sonar_value = sonar_value;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
