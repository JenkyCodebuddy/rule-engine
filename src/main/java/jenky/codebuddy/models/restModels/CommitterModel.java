package jenky.codebuddy.models.restModels;

public class CommitterModel {

    private String name;
    private String email;
    private String date;
    private String committerUsername;

    public CommitterModel(String name, String email, String date, String committerUsername){
        this.name = name;
        this.email = email;
        this.date = date;
        this.committerUsername = committerUsername;
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

    public String getCommitterUsername() {
        return committerUsername;
    }

    public void setCommitterUsername(String committerUsername) {
        this.committerUsername = committerUsername;
    }
}