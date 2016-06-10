package jenky.codebuddy.token.models;

import java.security.Key;

/**
 * Created by Fabian on 27-5-2016.
 */
public class Token {

    private String javaWebToken;
    private String id;
    private Key key;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return javaWebToken;
    }

    public void setToken(String token) {
        this.javaWebToken = token;
    }
}
