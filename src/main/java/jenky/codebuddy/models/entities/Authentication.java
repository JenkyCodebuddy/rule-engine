package jenky.codebuddy.models.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joost on 1-6-2016.
 */
@Entity
@Table(name = "authentication")
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int authentication_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "auth_key", length = 1000)
    private String auth_key;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public Authentication() {
    }

    public Authentication(User user, String auth_key, String token, Date created_at, Date updated_at, Date deleted_at) {
        this.user = user;
        this.auth_key = auth_key;
        this.token = token;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public int getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(int authentication_id) {
        this.authentication_id = authentication_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
