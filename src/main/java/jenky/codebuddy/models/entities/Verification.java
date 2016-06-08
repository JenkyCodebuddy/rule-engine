package jenky.codebuddy.models.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joost on 1-6-2016.
 */


@Entity
@Table(name = "verification")
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="verificaiton_id", unique = true, nullable = false)
    private int verificiation_id;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    private Date created_at;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Verification() {
    }

    public Verification(String code, Date created_at, User user) {
        this.code = code;
        this.created_at = created_at;
        this.user = user;
    }

    public int getVerificiation_id() {
        return verificiation_id;
    }

    public void setVerificiation_id(int verificiation_id) {
        this.verificiation_id = verificiation_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
