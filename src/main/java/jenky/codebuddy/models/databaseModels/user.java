package jenky.codebuddy.models.databaseModels;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class user {

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
    private Set<score> scores = new HashSet<score>(0);
    private Set<item> items = new HashSet<item>(0);
    private Set<achievement> achievements = new HashSet<achievement>(0);

    public user() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public Set<score> getScores() {
        return scores;
    }

    public void setScores(Set<score> scores) {
        this.scores = scores;
    }

    public Set<item> getItems() {
        return items;
    }

    public void setItems(Set<item> items) {
        this.items = items;
    }

    public Set<achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<achievement> achievements) {
        this.achievements = achievements;
    }
}
