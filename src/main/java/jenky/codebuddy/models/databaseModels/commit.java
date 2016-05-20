package jenky.codebuddy.models.databaseModels;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by joost (meme_lord) on 14-5-2016.
 */

@Entity
@Table(name = "commit")
public class Commit {

    @Id @GeneratedValue
    @Column(name = "commit_id")
    private String id;

    @OneToMany(mappedBy = "commit")
    private Set<Score> scores = new HashSet<Score>(0);

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public Commit() {
    }

    public Commit(String id, Set<Score> scores, Date created_at, Date updated_at, Date deleted_at) {
        this.id = id;
        this.scores = scores;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
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
