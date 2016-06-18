package jenky.codebuddy.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jenky.codebuddy.database.project.ProjectService;
import jenky.codebuddy.database.score.ScoreService;
import jenky.codebuddy.modelbuilders.ScoreModelBuilder;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gcm.Data;
import jenky.codebuddy.models.gcm.Message;
import jenky.codebuddy.models.gcm.Notification;
import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.UserCommit;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import wildtornado.scocalc.objects.Score;

import java.lang.reflect.Type;
import java.util.*;

/**
 * This service has as main function to save the score in the database
 */
public class ScoreUserServiceImpl implements ScoreUserService {

    private final String messageUrl = "https://fcm.googleapis.com/fcm/send";

    public ScoreUserServiceImpl() {

    }

    /**
     * Parses the headers and sends it to the appropriate builders. Afterwards saves the models
     * @param headers Response from the CI server
     */
    @Override
    public void parseHeaders(Map<String, String> headers){
        Gson gson = new Gson();
        Type sonar = new TypeToken<List<SonarResponse>>(){}.getType();
        List<SonarResponse> sonarResponseList = gson.fromJson(headers.get("sonarquberesponse").replaceAll("\\s",""), sonar);
        SonarResponse sonarResponse = sonarResponseList.get(0);
        UserCommit userCommit = createUserCommitModel(headers);
        ScoreModelBuilder scoreModelBuilder = new ScoreModelBuilder(sonarResponse, userCommit);
        saveUserScore(scoreModelBuilder.getScoreModel(), sonarResponse, userCommit);;
    }

    /**
     * @param metricsDataInputModel  contains the calculated score
     * @param sonarResponse  contains the calculated score from sonarqube
     * @param userCommit  contains information about the user who commited this
     */
    @Override
    public void saveUserScore(Score metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit){
        ScoreService scoreService = DatabaseFactory.getScoreService();
        Commit commit = createCommit(userCommit);
        List<Metric> metricsList = sonarResponse.getMsr();
        Set<jenky.codebuddy.models.entities.Score> scores = new HashSet<>(0);
        User user = DatabaseFactory.getUserService().getUserIfExists(userCommit.getEmail());
        for (Metric aMetricsList : metricsList) {
            jenky.codebuddy.models.entities.Score score = new jenky.codebuddy.models.entities.Score();
            score.setUser(user);
            score.setSonar_value(aMetricsList.getVal());
            score.setScore((int) getScoreByName(aMetricsList.getKey(), metricsDataInputModel));
            score.setCommit(commit);
            score.setMetric(DatabaseFactory.getMetricService().getMetricIfExists(aMetricsList.getKey()));
            scores.add(score);
            scoreService.save(score);
        }
        //TODO add to current coins
        user.setJenkycoins(metricsDataInputModel.getCoinsEarned());
        user.setScores(scores);
        user.setUpdated_at(new Date());
        //DatabaseFactory.getUserService().saveOrUpdate(user);
    }

    /**
     * Saves the project if it doesn't exists and return it
     * @param projectname
     */
    @Override
    public Project saveOrGetProjectIfExists(String projectname){
        ProjectService projectsService = DatabaseFactory.getProjectService();
        Project project = new Project();
        if (!projectsService.checkIfProjectExists(projectname)){
            project.setName(projectname);
            project.setCreated_at(new Date());
            projectsService.addProject(project);
        } else {
            project = projectsService.getProjectIfExists(projectname);
            project.setUpdated_at(new Date());
        }
        return project;
    }

    /**
     * Creates a commit using the information from the userCommit
     * @param userCommit
     * @return
     */
    @Override
    public Commit createCommit(UserCommit userCommit){
        Commit commit = new Commit();
        commit.setBranch(userCommit.getBranch());
        commit.setProject(saveOrGetProjectIfExists(filterRegex(userCommit.getProjectName())));
        commit.setCreated_at(new Date());
        commit.setSha(userCommit.getSha());
        return commit;
    }

    /**
     * This is created because the names the scorecalculator library uses
     * are different then sonar uses
     * @param name sonar name
     * @param metricsDataInputModel contains the calculated scores
     * @return calculated score for the sonar metric
     */
    @Override
    public double getScoreByName(String name, Score metricsDataInputModel){
        switch (name) {
            case "ncloc": return metricsDataInputModel.getLinesOfCodeScore();
            case "sqale_index": return metricsDataInputModel.getTechnicalDebtScore();
            case "duplicated_lines": return metricsDataInputModel.getCodeDuplicationScore();
            case "coverage": return metricsDataInputModel.getTestCoverageScore();
            case "violations": return metricsDataInputModel.getCodeViolationsScore();
            default: return 0;
        }
    }

    /**
     * @param email of the commiter
     * @return List of previouss scores
     */
    @Override
    public List<jenky.codebuddy.models.entities.Score> getPreviousScores(String email){
        return DatabaseFactory.getScoreService().getPreviousScores(email);
    }

    /**
     * Parses the headers to githubinfo
     * @param headers From the CI server
     * @return Map containing info about the committer
     */
    @Override
    public UserCommit createUserCommitModel(Map<String, String> headers){
        UserCommit userCommit = new UserCommit();
        userCommit.setUsername(headers.get("username"));
        userCommit.setProjectName(headers.get("projectname"));
        userCommit.setSha(headers.get("sha"));
        userCommit.setBranch(headers.get("branch"));
        userCommit.setEmail(headers.get("email"));
        return userCommit;
    }

    /**
     * Uses firebase to send a notifcation with a custom body
     * @param messageBody
     * @param notificationBody
     * @param id
     */
    @Override
    public void sendPush(String messageBody, String notificationBody, String id){
        Gson gson = new Gson();
        Data data =  new Data();
        Notification notification = new Notification();
        Message message = new Message();

        data.setMessage(messageBody);
        data.setTitle("Build info");

        notification.setTitle("Code buddy");
        notification.setBody(notificationBody);
        notification.setIcon("myicon");

        message.setData(data);
        message.setTo(id);
        message.setNotification(notification);

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(messageUrl);
            StringEntity params = new StringEntity(gson.toJson(message));
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "key=AIzaSyBQndUWi-dF7c-B5BrptQyKPhaTgjXHMV4");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * extract projectname from address like http://github.com/company/project.git
     * Splits at the fourth / and removes the last four characters (.git)
     * @param url
     * @return
     */
    @Override
    public String filterRegex(String url) {
        String[] paths = url.split("/");
        return paths[4].substring(0, paths[4].length() - 4);
    }
    /**
     * Generate tips for user
     * @param user
     */
    @Override
    public void generateTips(){
        List<Commit> lastCommits = DatabaseFactory.getCommitService().getCommitsFromUser(1);
        Commit commit1 = lastCommits.get(0);
        Set<jenky.codebuddy.models.entities.Score> score = commit1.getScores();
        System.out.println(score);
    }
}
