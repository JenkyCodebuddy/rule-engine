package jenky.codebuddy.models.gcm;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fabian on 17-6-2016.
 */
public class Message {

    @SerializedName("to")
    private String to;

    @SerializedName("notification")
    private Notification notification;

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
