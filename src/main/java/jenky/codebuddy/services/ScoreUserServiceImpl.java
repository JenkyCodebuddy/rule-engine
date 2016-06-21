package jenky.codebuddy.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.database.project.ProjectService;
import jenky.codebuddy.database.score.ScoreService;
import jenky.codebuddy.modelbuilders.ScoreModelBuilder;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.UserCommit;
import wildtornado.scocalc.objects.Score;

import java.lang.reflect.Type;
import java.util.*;

/**
 * This service has as main function to save the score in the database
 */
public class ScoreUserServiceImpl implements ScoreUserService {

    private User user;
    private MessagingService messagingService;
    private final String successColour = "#1472ff";
    private final String failColour = "#ff0000";
    public ScoreUserServiceImpl() {

    }

    /**
     * Parses the headers and sends it to the appropriate builders. Afterwards saves the models
     *
     * @param headers Response from the CI server
     */
    @Override
    public void parseHeaders(Map<String, String> headers) {
        this.messagingService = new MessagingService();
        UserCommit userCommit = createUserCommitModel(headers);
        String messageId = DatabaseFactory.getUserService().getUserIfExists(userCommit.getEmail()).getMessageToken();
        if (headers.get("buildresult").equals("\"SUCCESS\"")) {
            Gson gson = new Gson();
            Type sonar = new TypeToken<List<SonarResponse>>() {
            }.getType();
            List<SonarResponse> sonarResponseList = gson.fromJson(headers.get("sonarquberesponse").replaceAll("\\s", ""), sonar);
            SonarResponse sonarResponse = sonarResponseList.get(0);
            ScoreModelBuilder scoreModelBuilder = new ScoreModelBuilder(sonarResponse, userCommit);
            saveUserScore(scoreModelBuilder.getScoreModel(), sonarResponse, userCommit);
            this.messagingService.sendPush("Results are in, check your profile!", successColour, messageId);
        } else {
            this.messagingService.sendPush("uhoh you broke the build! No scores earned!", failColour, messageId);
        }
    }

    /**
     * @param metricsDataInputModel contains the calculated score
     * @param sonarResponse         contains the calculated score from sonarqube
     * @param userCommit            contains information about the user who commited this
     */
    @Override
    public void saveUserScore(Score metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit) {
        ScoreService scoreService = DatabaseFactory.getScoreService();
        Commit commit = createCommit(userCommit);
        List<Metric> metricsList = sonarResponse.getMsr();
        this.user = DatabaseFactory.getUserService().getUserIfExists(userCommit.getEmail());
        Set<jenky.codebuddy.models.entities.Score> scores = new HashSet<>(0);
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
        DatabaseFactory.getUserService().saveOrUpdate(user);
    }

    /**
     * Saves the project if it doesn't exists and return it
     *
     * @param projectname
     */
    @Override
    public Project saveOrGetProjectIfExists(String projectname) {
        ProjectService projectsService = DatabaseFactory.getProjectService();
        Project project = new Project();
        if (!projectsService.checkIfProjectExists(projectname)) {
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
     *
     * @param userCommit
     * @return
     */
    @Override
    public Commit createCommit(UserCommit userCommit) {
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
     *
     * @param name                  sonar name
     * @param metricsDataInputModel contains the calculated scores
     * @return calculated score for the sonar metric
     */
    @Override
    public double getScoreByName(String name, Score metricsDataInputModel) {
        switch (name) {
            case "ncloc":
                return metricsDataInputModel.getLinesOfCodeScore();
            case "sqale_index":
                return metricsDataInputModel.getTechnicalDebtScore();
            case "duplicated_lines":
                return metricsDataInputModel.getCodeDuplicationScore();
            case "coverage":
                return metricsDataInputModel.getTestCoverageScore();
            case "violations":
                return metricsDataInputModel.getCodeViolationsScore();
            default:
                return 0;
        }
    }

    /**
     * @param email of the commiter
     * @return List of previouss scores
     */
    @Override
    public List<jenky.codebuddy.models.entities.Score> getPreviousScores(String email) {
        return DatabaseFactory.getScoreService().getPreviousScores(email);
    }

    /**
     * Parses the headers to githubinfo
     *
     * @param headers From the CI server
     * @return Map containing info about the committer
     */
    @Override
    public UserCommit createUserCommitModel(Map<String, String> headers) {
        UserCommit userCommit = new UserCommit();
        userCommit.setUsername(headers.get("username"));
        userCommit.setProjectName(headers.get("projectname"));
        userCommit.setSha(headers.get("sha"));
        userCommit.setBranch(headers.get("branch"));
        userCommit.setEmail(headers.get("email"));
        return userCommit;
    }

    /**
     * extract projectname from address like http://github.com/company/project.git
     * Splits at the fourth / and removes the last four characters (.git)
     *
     * @param url
     * @return
     */
    @Override
    public String filterRegex(String url) {
        String[] paths = url.split("/");
        return paths[4].substring(0, paths[4].length() - 4);
    }

    /**
     * Generates tips if needed for the user. Tips are only generated when a user has at least 3 previous commits
     * TODO add push message
     * @param user
     */
    @Override
    public void generateTips(User user, String messageId, String projectName) {
        Random rand = new Random();
        List<Map<String, Double>> sonarValues = DatabaseFactory.getCommitService().getSonarValuesFromLastCommits(user.getUser_id());
        if (sonarValues != null && sonarValues.size() == 3) {
            Map<String, Double> averageScores = generateAverageScoresMap(sonarValues);
            List<String> metricsWhichNeedTips = checkWhichMetricsNeedTips(averageScores);
            if (rand.nextInt(3) + 1 == 3) { //random factor for when a tip is shown (1 in 3 chance right now), commented out for testing
                String metric = abbreviationMap().get(rand.nextInt(metricsWhichNeedTips.size())); //get random metric from metricWhichNeedTips list
                User userWithBestScoreForMetric = DatabaseFactory.getUserService().getUserWithHighestMetricScoreForProject(metric, projectName);
                if(userWithBestScoreForMetric != null){
                    this.messagingService.sendPush(
                            "If you want to improve the following metric: \" + metric + \", ask \" + userWithBestScoreForMetric.getEmail() + \"! He/she has the best score",
                            successColour,
                            messageId);
                }
                else{
                    System.out.println("No one is suitable to ask for tips");
                }
            }
        }
        else{
            System.out.println("There are not enough previous commits");
        }
    }

    /**
     * This method takes as input a list of maps. Each map contains the sonarvalues from a commit. It calculates the average sonarvalues from these commit maps.
     * @param sonarValues
     * @return
     */
    private Map<String, Double> generateAverageScoresMap(List<Map<String, Double>> sonarValues) {
        Map<String, Double> avgMap = new HashMap<String, Double>();
        for (String key : sonarValues.get(0).keySet()) {
            avgMap.put(key,
                    Math.floor((sonarValues.get(0).get(key) +
                            sonarValues.get(1).get(key) +
                            sonarValues.get(2).get(key)) / 3));
        }
        return avgMap;
    }

    /**
     * This method generates the list of strings of the metrics which need to be improved
     * @param avgSonarValues
     * @return
     */
    private List<String> checkWhichMetricsNeedTips(Map<String, Double> avgSonarValues) {
        Map<String, Double> sufficientMap = generateSufficientMap();
        avgSonarValues.remove("complexity"); // remove all non relevant metrics
        avgSonarValues.remove("sqale_index");
        avgSonarValues.remove("comment_lines");
        avgSonarValues.remove("ncloc");
        List<String> metricsWhichNeedTips = compareMaps(avgSonarValues, sufficientMap);
        return metricsWhichNeedTips;
    }

    /**
     * Method for generating a map which contains the values which we consider good enough
     * @return
     */
    private Map<String, Double> generateSufficientMap() {
        Map<String, Double> sufficientMap = new HashMap<String, Double>();
        sufficientMap.put("coverage", 100.0);
        //sufficientMap.put("complexity", 100.0);
        sufficientMap.put("minor_violations", 100.0);
        sufficientMap.put("duplicated_lines_density", 100.0);
        sufficientMap.put("duplicated_lines", 100.0);
        sufficientMap.put("violations", 100.0);
        sufficientMap.put("comment_lines_density", 100.0);
        //sufficientMap.put("sqale_index", 100.0);
        sufficientMap.put("critical_violations", 100.0);
        sufficientMap.put("blocker_violations", 100.0);
        sufficientMap.put("test_failures", 100.0);
        sufficientMap.put("major_violations", 100.0);
        sufficientMap.put("tests", 100.0);
        //sufficientMap.put("comment_lines", 100.0);
        //sufficientMap.put("ncloc", 100.0);
        sufficientMap.put("test_errors", 100.0);
        return sufficientMap;
    }

    /**
     * This method compares the average sonar values with the sufficientMap
     * @param avgMap
     * @param sufficientMap
     * @return
     */
    private List<String> compareMaps(Map<String, Double> avgMap, Map<String, Double> sufficientMap) {
        List<String> metricsWhichNeedTips = new ArrayList<String>();
        for (String key : sufficientMap.keySet()) {
            if (sufficientMap.get(key) < avgMap.get(key)) {
                metricsWhichNeedTips.add(key);
            }
        }
        return metricsWhichNeedTips;
    }

    private Map<String, String> abbreviationMap(){
        Map<String, String> abbreviationMap = new HashMap<String, String>();
        abbreviationMap.put("coverage", "code coverage");
        //abbreviationMap.put("complexity", "code complexity");
        abbreviationMap.put("minor_violations", "minor violations");
        abbreviationMap.put("duplicated_lines_density", "density of duplicated lines");
        abbreviationMap.put("duplicated_lines", "number of duplicated lines");
        abbreviationMap.put("violations", "code violations");
        abbreviationMap.put("comment_lines_density", "density of comments");
        //abbreviationMap.put("sqale_index", "technical debt");
        abbreviationMap.put("critical_violations", "critical violations");
        abbreviationMap.put("blocker_violations", "blocker violations");
        abbreviationMap.put("test_failures", "test failures");
        abbreviationMap.put("major_violations", "major violations");
        abbreviationMap.put("tests", "number of tests");
        //abbreviationMap.put("comment_lines", "number of comments");
        //abbreviationMap.put("ncloc", "non commented lines of code");
        abbreviationMap.put("test_errors", "test errors");
        return abbreviationMap;
    }
 }
