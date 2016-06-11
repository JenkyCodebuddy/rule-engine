package jenky.codebuddy.modelbuilders;

import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.UserCommit;
import jenky.codebuddy.services.ScoreUserService;
import wildtornado.scocalc.Calc;
import wildtornado.scocalc.objects.DataInput;
import wildtornado.scocalc.objects.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreModelBuilder {
    private DataInput metricsDataInputModel;
    private DataInput comparisonDataInputModel;
    private Score scoreModel = new Score();
    private Map<String, Double> metricsMap;

    public ScoreModelBuilder(SonarResponse sonarResponse, UserCommit userCommit) {
        createMap(sonarResponse);
        mapComparisonDataInputModel(userCommit.getEmail());
        createScoreModel();
    }

    /**
     * Creates a map from the SonarReponse, with metrics as key and their value as value
     * Afterwards call the method to map the data to the metricDataInputModel
     * @param sonarResponse
     */
    private void createMap(SonarResponse sonarResponse) {
        List<Metric> metricsList = sonarResponse.getMsr();
        this.metricsMap = new HashMap<>();
        for (Metric aMetricsList : metricsList) {
            this.metricsMap.put(aMetricsList.getKey(), aMetricsList.getVal());
        }
        mapMetricsData(this.metricsMap);
    }

    /**
     * Maps the sonarReponse to the metricDataInputModel
     * @param metricsMap
     */
    public void mapMetricsData(Map metricsMap) {
        this.metricsDataInputModel = new DataInput();
        this.metricsDataInputModel.setCommentPercentage(0);
        this.metricsDataInputModel.setLinesOfCode((Double) metricsMap.get("ncloc"));
        this.metricsDataInputModel.setTechnicalDebt((Double) metricsMap.get("sqale_index"));
        this.metricsDataInputModel.setCodeDuplication((Double) metricsMap.get("duplicated_lines"));
        this.metricsDataInputModel.setCodeDuplicationDensity((Double) metricsMap.get("duplicated_lines_density"));
        this.metricsDataInputModel.setCommentLines((Double) metricsMap.get("comment_lines"));
        this.metricsDataInputModel.setNumberOfTests((Double) metricsMap.get("tests"));
        this.metricsDataInputModel.setTestCoverage((Double) metricsMap.get("coverage"));
        this.metricsDataInputModel.setTestErrors((Double) metricsMap.get("test_errors"));
        this.metricsDataInputModel.setTestFailures((Double) metricsMap.get("test_failures"));
        this.metricsDataInputModel.setCodeViolations((Double) metricsMap.get("violations"));
        this.metricsDataInputModel.setCommentedOutCodeLines(0);
        this.metricsDataInputModel.setMajorViolations((Double) metricsMap.get("major_violations"));
        this.metricsDataInputModel.setMinorViolations((Double) metricsMap.get("minor_violations"));
        this.metricsDataInputModel.setCriticalViolations((Double) metricsMap.get("critical_violations"));
        this.metricsDataInputModel.setBlockerViolations((Double) metricsMap.get("blocker_violations"));
    }

    /**
     * Maps the previous commit to the comparisonDataInputModel
     * @param email
     */
    private void mapComparisonDataInputModel(String email) {
        List<jenky.codebuddy.models.entities.Score> previousScores = new ScoreUserService().getPreviousScores(email);
        HashMap<String, Double> previousScoresMap = new HashMap<String, Double>();
        this.comparisonDataInputModel = new DataInput();
        for (jenky.codebuddy.models.entities.Score score : previousScores) {
            previousScoresMap.put(score.getMetric().getName(), score.getSonar_value());
        }
        if (previousScoresMap.isEmpty()){
            this.comparisonDataInputModel.setCommentPercentage(0);
            this.comparisonDataInputModel.setLinesOfCode(0);
            this.comparisonDataInputModel.setTechnicalDebt(0);
            this.comparisonDataInputModel.setCodeDuplication(0);
            this.comparisonDataInputModel.setCodeDuplicationDensity(0);
            this.comparisonDataInputModel.setCommentLines(0);
            this.comparisonDataInputModel.setNumberOfTests(0);
            this.comparisonDataInputModel.setTestCoverage(0);
            this.comparisonDataInputModel.setTestErrors(0);
            this.comparisonDataInputModel.setTestFailures(0);
            this.comparisonDataInputModel.setCodeViolations(0);
            this.comparisonDataInputModel.setCommentedOutCodeLines(0);
            this.comparisonDataInputModel.setMajorViolations(0);
            this.comparisonDataInputModel.setMinorViolations(0);
            this.comparisonDataInputModel.setCriticalViolations(0);
            this.comparisonDataInputModel.setBlockerViolations(0);
        } else {
            this.comparisonDataInputModel.setCommentPercentage(0);
            this.comparisonDataInputModel.setLinesOfCode(previousScoresMap.get("ncloc"));
            this.comparisonDataInputModel.setTechnicalDebt(previousScoresMap.get("sqale_index"));
            this.comparisonDataInputModel.setCodeDuplication(previousScoresMap.get("duplicated_lines"));
            this.comparisonDataInputModel.setCodeDuplicationDensity(previousScoresMap.get("duplicated_lines_density"));
            this.comparisonDataInputModel.setCommentLines(previousScoresMap.get("comment_lines"));
            this.comparisonDataInputModel.setNumberOfTests(previousScoresMap.get("tests"));
            this.comparisonDataInputModel.setTestCoverage(previousScoresMap.get("coverage"));
            this.comparisonDataInputModel.setTestErrors(previousScoresMap.get("test_errors"));
            this.comparisonDataInputModel.setTestFailures(previousScoresMap.get("test_failures"));
            this.comparisonDataInputModel.setCodeViolations(previousScoresMap.get("violations"));
            this.comparisonDataInputModel.setCommentedOutCodeLines(0);
            this.comparisonDataInputModel.setMajorViolations(previousScoresMap.get("major_violations"));
            this.comparisonDataInputModel.setMinorViolations(previousScoresMap.get("minor_violations"));
            this.comparisonDataInputModel.setCriticalViolations(previousScoresMap.get("critical_violations"));
            this.comparisonDataInputModel.setBlockerViolations(previousScoresMap.get("blocker_violations"));
        }
    }

    public void createScoreModel(){
        Calc calculator = new Calc(this.metricsDataInputModel, this.comparisonDataInputModel);
        this.scoreModel = calculator.generateScore();
    }

    public DataInput getMetricsDataInputModel() {
        return metricsDataInputModel;
    }

    public void setMetricsDataInputModel(DataInput metricsDataInputModel) {
        this.metricsDataInputModel = metricsDataInputModel;
    }

    public DataInput getComparisonDataInputModel() {
        return comparisonDataInputModel;
    }

    public void setComparisonDataInputModel(DataInput comparisonDataInputModel) {
        this.comparisonDataInputModel = comparisonDataInputModel;
    }

    public Score getScoreModel() {
        return scoreModel;
    }

    public void setScoreModel(Score scoreModel) {
        this.scoreModel = scoreModel;
    }

    public Map<String, Double> getMetricsMap() {
        return metricsMap;
    }

    public void setMetricsMap(Map<String, Double> metricsMap) {
        this.metricsMap = metricsMap;
    }
}
