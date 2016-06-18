package jenky.codebuddy.models.gcm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

/**
 * Created by Fabian on 17-6-2016.
 */
public class test {

    public static void main(String[] args) throws UnirestException {
        Data data =  new Data();
        data.setMessage("test");
        data.setTitle("test");

        Message message = new Message();
        message.setData(data);
        message.setTo("dtr1hEmd3M4:APA91bHprc_uOuaNJbGMUjwuARMJry4acTvXERJMxMVbU0msvPbMFlELEJXRosnpHK7rQ2A-7doNNaDHKg7SNK4f10l_M6-hI5ZzvOUQK_LuylfjEU1OR3UtDm6S0-vsYIbGnDkt9SOJ");

        Gson gson = new Gson();

        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
            private Gson gson = new Gson();

            public <T> T readValue(String value, Class<T> valueType) {
                return gson.fromJson(value, valueType);
            }

            public String writeValue(Object value) {
                return gson.toJson(value);
            }
        });


        HttpResponse<JsonNode> postResponse = Unirest.post("https://fcm.googleapis.com/fcm/send")
                                .header("Content-Type", "application/json")
                                .header("Authorization", "key=AIzaSyBQndUWi-dF7c-B5BrptQyKPhaTgjXHMV4")
                                .body(message)
                                .asJson();
        System.out.println(postResponse.getStatus());
        System.out.println(postResponse.getStatusText());
        System.out.println(gson.toJson(message));
        //servertest.post(gson.toJson(message));
    }
}
