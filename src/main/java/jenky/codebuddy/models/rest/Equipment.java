package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Item;

import java.util.List;

/**
 * Created by joost on 9-6-2016.
 */
public class Equipment {

    private List<Item> equipment;
    private int responseCode;
    private String responseMessage;

    public Equipment() {
    }

    public Equipment(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public Equipment(List<Item> equipment, int responseCode) {
        this.equipment = equipment;
        this.responseCode = responseCode;
    }

    public List<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Item> equipment) {
        this.equipment = equipment;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
