package jenky.codebuddy.services;

import com.google.gson.Gson;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gcm.Message;
import jenky.codebuddy.models.gcm.Notification;
import jenky.codebuddy.models.rest.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Date;

/**
 * Created by joost on 17-6-2016.
 */
public class MessagingService {

    private final String messageUrl = "https://fcm.googleapis.com/fcm/send";


    public MessagingService() {
    }

    public Response saveMessagingTokenForUser(String authenticationToken, String messagingToken){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(authenticationToken).getUser();
        user.setMessageToken(messagingToken);
        user.setUpdated_at(new Date());
        DatabaseFactory.getUserService().saveOrUpdate(user);
        return new Response(200,"Notification has been enabled.");
    }

    public Response deleteMessagingTokenForUser(String authenticationToken){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(authenticationToken).getUser();
        user.setMessageToken("");
        DatabaseFactory.getUserService().saveOrUpdate(user);
        return new Response(200,"Notification disabled");
    }

    /**
     * Uses Firebase to send messages
     * @param messagePreview
     * @param colour
     * @param id
     */
    public void sendPush(String messagePreview, String content, String colour, String vibration, String id){
        Gson gson = new Gson();
        jenky.codebuddy.models.gcm.Data data =  new jenky.codebuddy.models.gcm.Data();
        Notification notification = new Notification();
        Message message = new Message();

        data.setMessage(messagePreview);
        data.setContent(content);
        data.setTitle("Code buddy");
        data.setColour(colour);
        data.setVibration(vibration);

        message.setData(data);
        message.setTo(id);
        message.setNotification(notification);

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(messageUrl);
            StringEntity params = new StringEntity(gson.toJson(message));
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "key=AIzaSyBQndUWi-dF7c-B5BrptQyKPhaTgjXHMV4");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
