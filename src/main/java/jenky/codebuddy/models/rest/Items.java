package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Item;

import java.util.List;

/**
 * Created by joost on 6-6-2016.
 */
public class Items {

    private List<Item> items;
    private int jenkycoins;
    private int responseCode;
    private String responseMessage;

    public Items() {

    }

    public Items(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public Items(List<Item> items, int jenkycoins, int responseCode) {
        this.items = items;
        this.jenkycoins = jenkycoins;
        this.responseCode = responseCode;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    public int getJenkycoins() {
        return jenkycoins;
    }

    public void setJenkycoins(int jenkycoins) {
        this.jenkycoins = jenkycoins;
    }
}
