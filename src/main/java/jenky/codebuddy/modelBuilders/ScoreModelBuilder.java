package jenky.codebuddy.modelbuilders;

import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import wildtornado.scocalc.Calc;
import wildtornado.scocalc.objects.DataInput;
import wildtornado.scocalc.objects.Score;
import wildtornado.scocalc.strategies.Calculator;

import java.util.*;

public class ScoreModelBuilder {
    DataInput metricsDataInputModel;
    DataInput comparisonDataInputModel;
    Score scoreModel = new Score();
    Map<String, Double> metricsMap;

    public ScoreModelBuilder(SonarResponse sonarResponse) {
        createMap(sonarResponse);
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
        mapComparisonDataInputModel(this.metricsMap);
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
     * @param metricsMap
     */
    private void mapComparisonDataInputModel(Map metricsMap) {
        this.comparisonDataInputModel = new DataInput();
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
    }

    /**
     * Hier gaat het helemaal fout. Als i debug dan zie je wel dat de models scores bevatten.
     * De score is altijd 0 die we terug krijgen van elke metric.
     * Dit is toch wel de manier om het te gebruiken?
     */
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
