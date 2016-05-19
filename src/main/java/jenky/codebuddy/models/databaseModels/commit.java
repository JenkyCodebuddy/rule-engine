package jenky.codebuddy.models.databaseModels;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joost (meme_lord) on 14-5-2016.
 */

@Entity
@Table(name = "commit")
public class commit {

    @Id @GeneratedValue
    private String id;

    @OneToMany
    @JoinColumn(name = "score_id")
    private score Score;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public commit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public score getScore() {
        return Score;
    }

    public void setScore(score score) {
        Score = score;
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
