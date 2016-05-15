package jenky.codebuddy.models.databaseModels;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Table;

import javax.validation.Valid;
import java.util.Date;


public class achievement {

    private int id;
    private String name;
    private String description;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public achievement(int id, String name, String description, Date created_at, Date updated_at, Date deleted_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
