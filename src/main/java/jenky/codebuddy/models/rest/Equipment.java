package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Item;

import java.util.List;

/**
 * Created by joost on 9-6-2016.
 */
public class Equipment {

    List<Item> equipment;
    String error;

    public Equipment() {
    }

    public Equipment(String error) {
        this.error = error;
    }

    public Equipment(List<Item> equipment) {
        this.equipment = equipment;
    }

    public List<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Item> equipment) {
        this.equipment = equipment;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
