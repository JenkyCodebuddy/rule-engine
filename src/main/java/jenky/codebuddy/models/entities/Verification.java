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
    @Column(name="id", unique = true, nullable = false)
    private int verficiation_id;

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

    public int getVerficiation_id() {
        return verficiation_id;
    }

    public void setVerficiation_id(int verficiation_id) {
        this.verficiation_id = verficiation_id;
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
