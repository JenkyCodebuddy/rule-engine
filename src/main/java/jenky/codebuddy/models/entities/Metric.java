package jenky.codebuddy.models.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joost on 14-5-2016.
 */

@Entity
@Table(name = "metric")
public class Metric {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metric_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_ast")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public Metric() {
    }

    public Metric(int id, String name, Date created_at, Date updated_at, Date deleted_at) {
        this.id = id;
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
