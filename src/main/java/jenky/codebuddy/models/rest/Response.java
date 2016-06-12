package jenky.codebuddy.models.rest;

/**
 * Created by joost on 4-6-2016.
 */
public class Response {

    private int responseCode;
    private String responseMessage;

    public Response(){
    }

    public Response(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
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
