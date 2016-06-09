package jenky.codebuddy.models.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by joost on 9-6-2016.
 */
@Entity
@Table(name = "achievement_has_user")
public class AchievementUser implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "progress")
    private double progress;

    public AchievementUser() {
    }

    public AchievementUser(Achievement achievement, User user, double progress) {
        this.achievement = achievement;
        this.user = user;
        this.progress = progress;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}


