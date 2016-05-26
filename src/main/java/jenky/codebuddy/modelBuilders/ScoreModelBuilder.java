package jenky.codebuddy.modelbuilders;

import jenky.codebuddy.BusinessLogicDB;
import org.json.JSONArray;
import org.json.JSONObject;
import wildtornado.scocalc.Calc;
import wildtornado.scocalc.objects.DataInput;
import wildtornado.scocalc.objects.Score;

public class ScoreModelBuilder {
    JSONObject metric;
    DataInput metricsDataInputModel;
    DataInput comparisonDataInputModel;
    Score scoreModel;
    Calc calculator;
    private String jsonString;

    private double codeViolations;
    private double codeDuplications;
    private double codeDuplicationDensity;
    private double numberOfTests;
    private double numberOfTestErrors;
    private double numberOfTestFailures;
    private double codeCoverage;
    private double technicalDebt;
    private double commentPercentage;
    private double linesOfCode;
    private double linesOfComments;

    public ScoreModelBuilder(String sonarqubeResponseJsonString) {
        readSonarqubeJson(sonarqubeResponseJsonString);
    }

    private void readSonarqubeJson(String jsonString) { //read entire sonarqube api JSON string and select the metricsDataInputModel

        jsonString = jsonString.replaceAll("\\s","");
        JSONArray json = new JSONArray(jsonString);
        JSONObject obj = json.getJSONObject(0);
        JSONArray metrics = obj.getJSONArray("msr");
        fillMetrics(metrics);
    }

    private void fillMetrics(JSONArray metrics) { //map the metricsDataInputModel from the json to variables
        BusinessLogicDB logic = new BusinessLogicDB();
        for (int i = 0; i < metrics.length(); i++) {
            metric = metrics.getJSONObject(i);
            switch (metric.getString("key")) { // not all metricsDataInputModel are supported + not yet expendable
                case "ncloc":
                    linesOfCode = metric.getDouble("val");
                    break;
                case "sqale_index":
                    technicalDebt = metric.getDouble("val");
                    break;
                case "comment_lines_density":
                    commentPercentage = metric.getDouble("val");
                    break;
                case "violations":
                    codeViolations= metric.getDouble("val");
                    break;
                case "tests":
                    numberOfTests = metric.getDouble("val");
                    break;
                case "test_failures":
                    numberOfTestFailures = metric.getDouble("val");
                    break;
                case "test_errors":
                    numberOfTestErrors = metric.getDouble("val");
                    break;
                case "coverage":
                    codeCoverage = metric.getDouble("val");
                    break;
                case "duplicated_lines":
                    codeDuplications = metric.getDouble("val");
                    break;
                case "duplicated_lines_density":
                    codeDuplicationDensity = metric.getDouble("val");
                    break;
                case "comment_lines":
                    linesOfComments = metric.getDouble("val");
                    break;
                default:
                    System.out.println("Unknown metric found: "+ metric.getString("key"));
                    break;
            }
            //logic.updateMetricTable(metric.getString("key"));
        }
        mapMetricsData();
        createComparisonDataInputModel();
    }

    private void mapMetricsData() { //map the metric variables to a new datainput object
        DataInput metricDataInputModel = new DataInput();
        metricDataInputModel.setCommentPercentage(commentPercentage);
        metricDataInputModel.setLinesOfCode(linesOfCode);
        metricDataInputModel.setTechnicalDebt(technicalDebt);
        metricDataInputModel.setCodeDuplication(codeDuplications);
        metricDataInputModel.setCodeDuplicationDensity(codeDuplicationDensity);
        metricDataInputModel.setCommentLines(linesOfComments);
        metricDataInputModel.setNumberOfTests(numberOfTests);
        metricDataInputModel.setTestCoverage(codeCoverage);
        metricDataInputModel.setTestErrors(numberOfTestErrors);
        metricDataInputModel.setTestFailures(numberOfTestFailures);
        metricDataInputModel.setCodeViolations(codeViolations);
        metricDataInputModel.setCommentedOutCodeLines(0);
        setMetricsDataInputModel(metricDataInputModel);
    }

    private void createComparisonDataInputModel() {
        DataInput comparisonDataInputModel = new DataInput();
        comparisonDataInputModel.setCommentPercentage(0);
        comparisonDataInputModel.setLinesOfCode(0);
        comparisonDataInputModel.setTechnicalDebt(0);
        comparisonDataInputModel.setCodeDuplication(0);
        comparisonDataInputModel.setCodeDuplicationDensity(0);
        comparisonDataInputModel.setCommentLines(0);
        comparisonDataInputModel.setNumberOfTests(0);
        comparisonDataInputModel.setTestCoverage(0);
        comparisonDataInputModel.setTestErrors(0);
        comparisonDataInputModel.setTestFailures(0);
        comparisonDataInputModel.setCodeViolations(0);
        comparisonDataInputModel.setCommentedOutCodeLines(0);
        setComparisonDataInputModel(comparisonDataInputModel);
    }

    public void createScoreModel(){
        calculator = new Calc(getMetricsDataInputModel(), getComparisonDataInputModel());
        setScoreModel(calculator.generateScore());
    }

    private DataInput getMetricsDataInputModel() {
        return metricsDataInputModel;
    }

    private void setMetricsDataInputModel(DataInput metrics) {
        this.metricsDataInputModel = metrics;
    }

    private DataInput getComparisonDataInputModel() {
        return comparisonDataInputModel;
    }

    private void setComparisonDataInputModel(DataInput comparisonDataInputModel) {
        this.comparisonDataInputModel = comparisonDataInputModel;
    }

    public Score getScoreModel() {
        return scoreModel;
    }

    public void setScoreModel(Score scoreModel) {
        this.scoreModel = scoreModel;
    }
}
