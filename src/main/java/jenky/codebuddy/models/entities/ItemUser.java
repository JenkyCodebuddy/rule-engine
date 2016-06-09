package jenky.codebuddy.models.entities;

import javax.persistence.*;

/**
 * Created by joost on 9-6-2016.
 */
@Entity
@Table(name = "item_has_user")
public class ItemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "equipped")
    private boolean equipped;

    public ItemUser() {
    }

    public ItemUser(Item item, User user, boolean equipped) {
        this.item = item;
        this.user = user;
        this.equipped = equipped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }
}
