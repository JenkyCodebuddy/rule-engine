package jenky.codebuddy.models.databaseModels;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "score")
public class score {

    @Id @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private user User;

    @OneToOne
    @JoinColumn(name = "project_id")
    private project Project;

    @OneToOne
    @JoinColumn(name = "metric_id")
    private metric Metric;

    @Column(name = "sonar_value")
    private double sonar_value;

    @Column(name = "score")
    private int score;
    //????
    private Set<commit> commits  = new HashSet<commit>(0);

    @Column(name = "project")
    private project project;

    @Column(name = "metric")
    private metric metric;

    public score() {
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
