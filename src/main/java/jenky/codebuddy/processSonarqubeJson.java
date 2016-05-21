package jenky.codebuddy;

import org.json.JSONArray;
import org.json.JSONObject;
import wildtornado.scocalc.Calc;
import wildtornado.scocalc.objects.DataInput;
import wildtornado.scocalc.objects.Score;

public class processSonarqubeJson {
    JSONObject metric;
    DataInput metricsDataInputModel;
    DataInput comparisonDataInputModel;
    Score scoreModel;
    Calc calculator;
    private String jsonString;
    private double codeComplexity;
    private double codeDuplicationDensity;
    private double codeViolationsDensity;
    private double numberOfTests;
    private double technicalDebt;
    private double commentPercentage;
    private double linesOfCode;
    private double linesOfComments;

    public processSonarqubeJson(String sonarqubeResponseJsonString) {
        readSonarqubeJson(sonarqubeResponseJsonString);
    }

    private void readSonarqubeJson(String jsonString) { //read entire sonarqube api JSON string and select the metricsDataInputModel
        JSONArray json = new JSONArray(jsonString);
        JSONObject obj = json.getJSONObject(0);
        JSONArray metrics = obj.getJSONArray("msr");
        fillMetrics(metrics);
    }

    private void fillMetrics(JSONArray metrics) { //map the metricsDataInputModel from the json to variables
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
                case "complexity":
                    codeComplexity = metric.getDouble("val");
                    break;
                case "tests":
                    numberOfTests = metric.getDouble("val");
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
        }
        mapMetricsData();
        createComparisonDataInputModel();
    }

    private void mapMetricsData() { //map the metric variables to a new datainput object
        DataInput metricDataInputModel = new DataInput();
        metricDataInputModel.setCodeComplexity(codeComplexity);
        metricDataInputModel.setCommentPercentage(commentPercentage);
        metricDataInputModel.setLinesOfCode(linesOfCode);
        metricDataInputModel.setTechnicalDebt(technicalDebt);
        metricDataInputModel.setCodeDuplicationDensity(codeDuplicationDensity);
        metricDataInputModel.setCodeViolationsDensity(codeViolationsDensity);
        metricDataInputModel.setCommentLines(linesOfComments);
        setMetricsDataInputModel(metricDataInputModel);
    }

    private void createComparisonDataInputModel() {
        DataInput comaprisonDataInputModel = new DataInput();
        comaprisonDataInputModel.setCodeComplexity(0);
        comaprisonDataInputModel.setCommentPercentage(0);
        comaprisonDataInputModel.setLinesOfCode(0);
        comaprisonDataInputModel.setTechnicalDebt(0);
        comaprisonDataInputModel.setCodeDuplicationDensity(0);
        comaprisonDataInputModel.setCodeViolationsDensity(0);
        comaprisonDataInputModel.setCommentLines(0);
        setComparisonDataInputModel(comaprisonDataInputModel);
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
