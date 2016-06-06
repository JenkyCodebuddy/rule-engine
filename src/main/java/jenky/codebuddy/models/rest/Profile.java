package jenky.codebuddy.models.rest;



import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.entities.Commit;

import java.util.List;

public class Profile {

    private User user;
    private List<UserCommit> userCommits;
    private double totalScore;
    private double avgScore;
    private double achievementCount;
    private double projectCount;
    private String error;

    public Profile() {
    }

    public Profile(User user, List<Commit> commits, double totalScore, double avgScore, double achievementCount, double projectCount) {
        this.user = user;
        this.commits = commits;
        this.totalScore = totalScore;
        this.avgScore = avgScore;
        this.achievementCount = achievementCount;
        this.projectCount = projectCount;
    }

    public Profile(String error){
        this.error=error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserCommit> getUserCommits() {
        return userCommits;
    }

    public void setUserCommits(List<UserCommit> userCommits) {
        this.userCommits = userCommits;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public double getAchievementCount() {
        return achievementCount;
    }

    public void setAchievementCount(double achievementCount) {
        this.achievementCount = achievementCount;
    }

    public double getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(double projectCount) {
        this.projectCount = projectCount;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
