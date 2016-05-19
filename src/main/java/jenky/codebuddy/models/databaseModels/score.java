package jenky.codebuddy.models.databaseModels;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "score")
public class Score {

    @Id @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private jenky.codebuddy.models.databaseModels.User User;

    @OneToOne
    @JoinColumn(name = "project_id")
    private jenky.codebuddy.models.databaseModels.Project Project;

    @OneToOne
    @JoinColumn(name = "metric_id")
    private jenky.codebuddy.models.databaseModels.Metric Metric;

    @Column(name = "sonar_value")
    private double sonar_value;

    @Column(name = "score")
    private int score;
    //????
    private Set<Commit> Commits = new HashSet<Commit>(0);

    @Column(name = "project")
    private jenky.codebuddy.models.databaseModels.Project Project;

    @Column(name = "metric")
    private jenky.codebuddy.models.databaseModels.Metric Metric;

    public Score() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Commit> getCommits() {
        return Commits;
    }

    public void setCommits(Set<Commit> Commits) {
        this.Commits = Commits;
    }

    public jenky.codebuddy.models.databaseModels.Project getProject() {
        return Project;
    }

    public void setProject(jenky.codebuddy.models.databaseModels.Project Project) {
        this.Project = Project;
    }

    public jenky.codebuddy.models.databaseModels.Metric getMetric() {
        return Metric;
    }

    public void setMetric(jenky.codebuddy.models.databaseModels.Metric Metric) {
        this.Metric = Metric;
    }
}
