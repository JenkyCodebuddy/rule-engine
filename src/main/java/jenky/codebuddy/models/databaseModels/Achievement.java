package jenky.codebuddy.models.databaseModels;


import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="achievement")
public class Achievement {

    @Id @GeneratedValue
    @Column(name = "achievement_id")
    private int id;

    @ManyToMany
    @JoinTable(name = "achievement_has_user", joinColumns = { @JoinColumn(name = "achievement_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users = new HashSet<User>();

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public Achievement() {
    }

    public Achievement(int id, Set<User> users, String name, String description, Date created_at, Date updated_at, Date deleted_at) {
        this.id = id;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
