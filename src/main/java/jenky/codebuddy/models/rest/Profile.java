package jenky.codebuddy.models.rest;


import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Item;

import java.util.List;

public class Profile {

    private List<Item> equippedItems;
    private List<Commit> commits;
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

    public Profile(List<Item> equippedItems, List<Commit> commits, double totalScore, double avgScore, double achievementCount, double projectCount, int responseCode) {
        this.equippedItems = equippedItems;
        this.commits = commits;
        this.totalScore = totalScore;
        this.avgScore = avgScore;
        this.achievementCount = achievementCount;
        this.projectCount = projectCount;
        this.responseCode = responseCode;
    }

    public Profile(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    public void setEquippedItems(List<Item> equippedItems) {
        this.equippedItems = equippedItems;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> userCommits) {
        this.commits = userCommits;
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
