package jenky.codebuddy.models.rest;


import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;

import java.util.List;

public class Profile {

    private User user;
    private List<Item> equipment;
    private List<Commit> commits;
    private double projectScore;
    private double totalScore;
    private double avgScore;
    private double achievementCount;
    private double projectCount;
    private int responseCode;
    private String responseMessage;

    public Profile() {
    }

    public Profile(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public Profile(User user, List<Item> equipment, List<Commit> commits, double projectScore, double totalScore, double avgScore, double achievementCount, double projectCount, int responseCode) {
        this.user = user;
        this.equipment = equipment;
        this.commits = commits;
        this.projectScore = projectScore;
        this.totalScore = totalScore;
        this.avgScore = avgScore;
        this.achievementCount = achievementCount;
        this.projectCount = projectCount;
        this.responseCode = responseCode;
    }

    public Profile(int responseCode) {
        this.responseCode = responseCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getProjectScore() {
        return projectScore;
    }

    public void setProjectScore(double projectScore) {
        this.projectScore = projectScore;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Item> equipment) {
        this.equipment = equipment;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
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

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
