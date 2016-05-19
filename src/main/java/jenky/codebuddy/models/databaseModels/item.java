package jenky.codebuddy.models.databaseModels;

import java.util.Date;

/**
 * Created by joost (meme_lord...) on 14-5-2016.
 */
public class item {

    private int id;
    private String name;
    private String type;
    private String image;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public item(int id, String name, String type, String image, Date created_at, Date updated_at, Date deleted_at) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
