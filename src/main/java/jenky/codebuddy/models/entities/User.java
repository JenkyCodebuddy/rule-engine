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

    //score mapping
    @OneToMany(mappedBy = "user")
    private Set<Score> Scores = new HashSet<Score>(0);

    public User() {
    }

    public User(String username, String password, Date created_at, Date updated_at, Date deleted_at, int jenkycoins) {

        this.email = username;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.jenkycoins = jenkycoins;

    }

    public int getUser_id() {
        return user_id;
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

    public Set<Score> getScores() {
        return Scores;
    }

    public void setScores(Set<Score> Scores) {
        this.Scores = Scores;
    }


}
