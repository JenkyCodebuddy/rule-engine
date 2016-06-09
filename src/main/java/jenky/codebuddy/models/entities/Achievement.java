package jenky.codebuddy.models.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="achievement")
public class Achievement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private int id;

    @OneToMany(mappedBy = "achievement")
    private Set<AchievementUser> achievementusers = new HashSet<AchievementUser>();

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "completed_on")
    private Date completed_on;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public Achievement() {
    }

    public Achievement(Set<AchievementUser> achievementusers, String name, String description, Date completed_on, Date created_at, Date updated_at, Date deleted_at) {
        this.achievementusers = achievementusers;
        this.name = name;
        this.description = description;
        this.completed_on = completed_on;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Set<AchievementUser> getAchievementusers() {
        return achievementusers;
    }

    public void setAchievementusers(Set<AchievementUser> achievementusers) {
        this.achievementusers = achievementusers;
    }

    public Date getCompleted_on() {
        return completed_on;
    }

    public void setCompleted_on(Date completed_on) {
        this.completed_on = completed_on;
    }
}
