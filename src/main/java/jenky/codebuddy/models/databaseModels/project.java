package jenky.codebuddy.models.databaseModels;

import java.util.Date;

/**
 * Created by joost on 14-5-2016.
 */
public class project {

    private int id;
    private int customer_id;
    private String name;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public project(int id, int customer_id, String name, Date created_at, Date updated_at, Date deleted_at) {
        this.id = id;
        this.customer_id = customer_id;
        this.name = name;
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

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
