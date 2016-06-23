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
    private final String vibration = "0";
    private String messageId;

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
            generateTips(this.user, messageId, filterRegex(userCommit.getProjectName()));
        } else {
            this.messagingService.sendPush("uhoh you broke the build! No scores earned!", "uhoh you broke the build! No scores earned!", failColour, "1000", messageId);
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
        user.setJenkycoins((metricsDataInputModel.getCoinsEarned() + 5) * 90);
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
     * Tips are generated with a 1 in 3 chance
     * If there is no user with the highest score for a metric, no tip is generated
     *
     * @param user
     */
    @Override
    public void generateTips(User user, String messageId, String projectName) {
        Random rand = new Random();
        List<Map<String, Double>> sonarValues = DatabaseFactory.getCommitService().getSonarValuesFromLastCommits(user.getUser_id());
        if (sonarValues != null && sonarValues.size() == 3) {
            Map<String, Double> averageScores = generateAverageScoresMap(sonarValues);
            List<String> metricsWhichNeedTips = checkWhichMetricsNeedTips(averageScores);
            //if (rand.nextInt(3) + 1 == 3) {
                String metric = metricsWhichNeedTips.get(rand.nextInt(metricsWhichNeedTips.size()));
                User userWithBestScoreForMetric = DatabaseFactory.getUserService().getUserWithHighestMetricScoreForProject(metric, projectName);
                if (userWithBestScoreForMetric != null && userWithBestScoreForMetric.getUser_id() != user.getUser_id()) {
                    this.messagingService.sendPush(
                            "Successful build! Check your profile for results",
                            "If you want to improve the following metric: " + abbreviationMap().get(metric) + ", ask " + userWithBestScoreForMetric.getEmail() + "! He/she has the best score",
                            successColour,
                            "50",
                            messageId);
                }
            }
        //}
    }

    /**
     * This method takes as input a list of maps. Each map contains the sonarvalues from a commit. It calculates the average sonarvalues from these commit maps.
     *
     * @param sonarValues
     * @return
     */
    private Map<String, Double> generateAverageScoresMap(List<Map<String, Double>> sonarValues) {
        Map<String, Double> avgMap = new HashMap<>();
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
     *
     * @param avgSonarValues
     * @return
     */
    private List<String> checkWhichMetricsNeedTips(Map<String, Double> avgSonarValues) {
        Map<String, Double> sufficientMap = generateSufficientMap();
        avgSonarValues.remove("complexity"); // remove all non relevant metrics
        avgSonarValues.remove("sqale_index");
        avgSonarValues.remove("comment_lines");
        avgSonarValues.remove("duplicated_lines");
        avgSonarValues.remove("ncloc");
        List<String> metricsWhichNeedTips = compareMaps(avgSonarValues, sufficientMap);
        return metricsWhichNeedTips;
    }

    /**
     * Method for generating a map which contains the values which we consider good enough
     *
     * @return
     */
    private Map<String, Double> generateSufficientMap() {
        Map<String, Double> sufficientMap = new HashMap<>();
        sufficientMap.put("coverage", 10.0); //lower
        sufficientMap.put("tests", 5.0); //lower
        sufficientMap.put("comment_lines_density", 5.0); //lower
        sufficientMap.put("minor_violations", 30.0); //higher
        sufficientMap.put("duplicated_lines_density", 5.0); //higher
        sufficientMap.put("violations", 20.0); //higher
        sufficientMap.put("critical_violations", 3.0); //higher
        sufficientMap.put("blocker_violations", 1.0); //higher
        sufficientMap.put("test_failures", 3.0); //higher
        sufficientMap.put("major_violations", 5.0); //higher
        sufficientMap.put("test_errors", 3.0); //higher
        return sufficientMap;
    }

    /**
     * This method compares two maps with sonar values with the sufficientMap
     * mapList.get(0) contains values which should be lower than the sufficientMap
     * mapList.get(1) contains values which should be higher than the sufficientMap
     * Finally a list of strings is returned. This list contains the names of all metrics which should be improved
     *
     * @param avgMap
     * @param sufficientMap
     * @return
     */
    private List<String> compareMaps(Map<String, Double> avgMap, Map<String, Double> sufficientMap) {
        List<String> metricsWhichNeedTips = new ArrayList<String>();
        List<Map<String, Double>> mapList = splitAvgMap(avgMap);
        for (String key : sufficientMap.keySet()) {
            if (mapList.get(0).containsKey(key)) {
                if (mapList.get(0).get(key) < sufficientMap.get(key)){ metricsWhichNeedTips.add(key);}
            }
            if (mapList.get(1).containsKey(key)) {
                if (mapList.get(1).get(key) > sufficientMap.get(key)){ metricsWhichNeedTips.add(key);}
            }
        }
        return metricsWhichNeedTips;
    }

    /**
     * This method generates a map which is used for converting the abbreviations to their metric names
     * @return
     */

    private Map<String, String> abbreviationMap() {
        Map<String, String> abbreviationMap = new HashMap<>();
        abbreviationMap.put("coverage", "code coverage");
        abbreviationMap.put("minor_violations", "minor violations");
        abbreviationMap.put("duplicated_lines_density", "density of duplicated lines");
        abbreviationMap.put("violations", "code violations");
        abbreviationMap.put("comment_lines_density", "comment density");
        abbreviationMap.put("critical_violations", "critical violations");
        abbreviationMap.put("blocker_violations", "blocker violations");
        abbreviationMap.put("test_failures", "test failures");
        abbreviationMap.put("major_violations", "major violations");
        abbreviationMap.put("tests", "number of tests");
        abbreviationMap.put("test_errors", "test errors");
        return abbreviationMap;
    }

    /**
     * This method splits the map containing the average sonar values in 2
     * One map contains sonar values which should be lower than the sufficientMap
     * The other map contains sonar values which should be higher than the sufficientMap
     * @param originalAvgMap
     * @return
     */

    private List<Map<String, Double>> splitAvgMap(Map<String, Double> originalAvgMap) {
        List<Map<String, Double>> mapList = new ArrayList<Map<String, Double>>();
        Map<String, Double> avgMapLower = new HashMap<>();
        Map<String, Double> avgMapHigher = new HashMap<>();
        avgMapLower.put("coverage", originalAvgMap.get("coverage"));
        avgMapLower.put("tests", originalAvgMap.get("tests"));
        avgMapLower.put("comment_lines_density", originalAvgMap.get("comment_lines_density"));
        for (String key : avgMapLower.keySet()) {
            originalAvgMap.remove(key);
        }
        avgMapHigher = originalAvgMap;
        mapList.add(avgMapHigher);
        mapList.add(avgMapLower);
        return mapList;
    }
}

