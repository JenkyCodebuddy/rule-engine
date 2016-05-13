package jenky.codebuddy;

import wildtornado.scocalc.Calc;
import wildtornado.scocalc.objects.Score;

public class callApi { //class for making API calls ayy lmao

    private String url;
    private String username;
    private String password;
    private String response;
    private String requestType;


    public callApi(String url, String username, String password, String requestType) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.requestType = requestType;
        apiRequest(url,username,password,requestType);
    }

    private void apiRequest(String url, String username, String password, String requestType) { //APIrequest method, makes requests using RestClient.class
        RestClient client = new RestClient(username,password);
        response = "";
        try {
            switch (requestType) {      //determine whether to POST or GET
                case "POST":
                    response = client.post(url, "");
                    break;
                case "GET":
                    response = client.get(url);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
         setResponse(response);
    }

    public String getResponse() {   //getters and setters for retrieving the API json response string
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

