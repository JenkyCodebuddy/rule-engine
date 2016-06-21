package jenky.codebuddy.models.gcm;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fabian on 17-6-2016.
 */
public class Data {

    @SerializedName("title")
    private String title;
    @SerializedName("message")
    private String message;

    @SerializedName("colour")
    private String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
