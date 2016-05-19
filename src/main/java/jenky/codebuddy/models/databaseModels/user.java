package jenky.codebuddy.models.databaseModels;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "customer")
    //private customer customer_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    @Column(name = "jenkycoins")
    private int jenkycoins;

    //?????
    private Set<Score> Scores = new HashSet<Score>(0);
    private Set<Item> Items = new HashSet<Item>(0);
    private Set<Achievement> Achievements = new HashSet<Achievement>(0);

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getJenkycoins() {
        return jenkycoins;
    }

    public void setJenkycoins(int jenkycoins) {
        this.jenkycoins = jenkycoins;
    }

    public Set<Score> getScores() {
        return Scores;
    }

    public void setScores(Set<Score> Scores) {
        this.Scores = Scores;
    }

    public Set<Item> getItems() {
        return Items;
    }

    public void setItems(Set<Item> Items) {
        this.Items = Items;
    }

    public Set<Achievement> getAchievements() {
        return Achievements;
    }

    public void setAchievements(Set<Achievement> Achievements) {
        this.Achievements = Achievements;
    }
}
