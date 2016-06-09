package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Item;

import java.util.List;

/**
 * Created by joost on 6-6-2016.
 */
public class Items {

    private List<Item> items;
    private String error;

    public Items() {

    }

    public Items(String error) {
        this.error = error;
    }

    public Items(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
