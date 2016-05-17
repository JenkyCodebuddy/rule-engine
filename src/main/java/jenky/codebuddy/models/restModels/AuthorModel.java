package jenky.codebuddy.models.restModels;

import java.util.Date;

public class AuthorModel {

    private String name;
    private String email;
    private String date;
    private String authorUsername;

    public AuthorModel(String name, String email, String date, String authorUsername){
        this.name = name;
        this.email = email;
        this.date = date;
        this.authorUsername = authorUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }
}
