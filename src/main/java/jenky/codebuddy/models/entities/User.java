package jenky.codebuddy.models.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", unique = true, nullable = false)
    private int user_id;

    @Column(name = "email")
    private String email;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "verification")
    private Verification verfication;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "authentication")
    private Authentication authentication;

    //score mapping
    @OneToMany(mappedBy = "user")
    private Set<Score> scores = new HashSet<Score>(0);

    @OneToMany(mappedBy = "user")
    private Set<AchievementUser> achievements = new HashSet<AchievementUser>();

    @OneToMany(mappedBy = "user")
    private Set<ItemUser> itemusers = new HashSet<ItemUser>();

    public User() {
    }

    public User(String email, String password, Date created_at, Date updated_at, Date deleted_at, int jenkycoins, Verification verfication, Authentication authentication, Set<Score> scores, Set<AchievementUser> achievements, Set<ItemUser> itemusers) {
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.jenkycoins = jenkycoins;
        this.verfication = verfication;
        this.authentication = authentication;
        this.scores = scores;
        this.achievements = achievements;
        this.itemusers = itemusers;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Verification getVerfication() {
        return verfication;
    }

    public void setVerfication(Verification verfication) {
        this.verfication = verfication;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Set<AchievementUser> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<AchievementUser> achievements) {
        this.achievements = achievements;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Set<ItemUser> getItemusers() {
        return itemusers;
    }

    public void setItemusers(Set<ItemUser> itemusers) {
        this.itemusers = itemusers;
    }
}
