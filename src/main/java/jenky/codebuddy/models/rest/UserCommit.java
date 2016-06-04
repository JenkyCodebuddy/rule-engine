package jenky.codebuddy.models.rest;

public class UserCommit {

    String username;
    String email;
    String branch;
    String sha;
    String projectName;

    public UserCommit(String username, String email, String branch, String sha, String projectName) {
        this.username = username;
        this.email = email;
        this.branch = branch;
        this.sha = sha;
        this.projectName = projectName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
