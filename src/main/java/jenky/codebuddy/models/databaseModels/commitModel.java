package jenky.codebuddy.models.databaseModels;

import java.util.Date;

/**
 * Created by joost on 14-5-2016.
 */
public class commitModel {

    private String id;
    private int score_id;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public commitModel(String id, int score_id, Date created_at, Date updated_at, Date deleted_at) {
        this.id = id;
        this.score_id = score_id;
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

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
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
