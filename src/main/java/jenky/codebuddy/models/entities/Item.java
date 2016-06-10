package jenky.codebuddy.models.entities;

import javax.persistence.*;
import javax.websocket.ClientEndpoint;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by joost (...) on 14-5-2016.
 */

@Entity
@Table(name = "item")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int id;

    @OneToMany(mappedBy= "item")
    private Set<ItemUser> itemusers = new HashSet<ItemUser>();

    @Column(name = "name")
    private String name;

    @Column(name = "item_type")
    private String type;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "price")
    private double price;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public Item() {

    }

    public Item(Set<ItemUser> itemusers, String name, String type, String image, double price, Date created_at, Date updated_at, Date deleted_at) {
        this.itemusers = itemusers;
        this.name = name;
        this.type = type;
        this.image = image;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public Set<ItemUser> getItemusers() {
        return itemusers;
    }

    public void setItemusers(Set<ItemUser> itemusers) {
        this.itemusers = itemusers;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
