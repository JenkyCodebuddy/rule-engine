package jenky.codebuddy.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "commit_id")
    private Commit commit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "metric_id")
    private Metric metric;

    @Column(name = "sonar_value")
    private double sonar_value;

    @Column(name = "score")
    private double score;

    public Score() {
    }

    public Score(User user, Commit commit, Metric metric, double sonar_value, double score) {
        this.user = user;
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

    public double getSonar_value() {
        return sonar_value;
    }

    public void setSonar_value(double sonar_value) {
        this.sonar_value = sonar_value;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }



}
