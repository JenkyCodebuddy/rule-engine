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
    private double majorViolations;
    private double minorViolations;
    private double criticalViolations;
    private double blockerViolations;

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
                case "major_violations":
                    majorViolations = metric.getDouble("val");
                    break;
                case "minor_violations":
                    minorViolations = metric.getDouble("val");
                    break;
                case "blocker_violations":
                    blockerViolations = metric.getDouble("val");
                    break;
                case "critical_violations":
                    criticalViolations = metric.getDouble("val");
                    break;
                default:
                    System.out.println("Unknown metric found: "+ metric.getString("key"));
                    break;
            }
            logic.updateMetricTable(metric.getString("key"));
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
        metricDataInputModel.setMajorViolations(majorViolations);
        metricDataInputModel.setMinorViolations(minorViolations);
        metricDataInputModel.setCriticalViolations(criticalViolations);
        metricDataInputModel.setBlockerViolations(blockerViolations);
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
        comparisonDataInputModel.setMajorViolations(0);
        comparisonDataInputModel.setMinorViolations(0);
        comparisonDataInputModel.setCriticalViolations(0);
        comparisonDataInputModel.setBlockerViolations(0);
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

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public double getCodeViolations() {
        return codeViolations;
    }

    public void setCodeViolations(double codeViolations) {
        this.codeViolations = codeViolations;
    }

    public double getCodeDuplications() {
        return codeDuplications;
    }

    public void setCodeDuplications(double codeDuplications) {
        this.codeDuplications = codeDuplications;
    }

    public double getCodeDuplicationDensity() {
        return codeDuplicationDensity;
    }

    public void setCodeDuplicationDensity(double codeDuplicationDensity) {
        this.codeDuplicationDensity = codeDuplicationDensity;
    }

    public double getNumberOfTests() {
        return numberOfTests;
    }

    public void setNumberOfTests(double numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    public double getNumberOfTestErrors() {
        return numberOfTestErrors;
    }

    public void setNumberOfTestErrors(double numberOfTestErrors) {
        this.numberOfTestErrors = numberOfTestErrors;
    }

    public double getNumberOfTestFailures() {
        return numberOfTestFailures;
    }

    public void setNumberOfTestFailures(double numberOfTestFailures) {
        this.numberOfTestFailures = numberOfTestFailures;
    }

    public double getCodeCoverage() {
        return codeCoverage;
    }

    public void setCodeCoverage(double codeCoverage) {
        this.codeCoverage = codeCoverage;
    }

    public double getTechnicalDebt() {
        return technicalDebt;
    }

    public void setTechnicalDebt(double technicalDebt) {
        this.technicalDebt = technicalDebt;
    }

    public double getCommentPercentage() {
        return commentPercentage;
    }

    public void setCommentPercentage(double commentPercentage) {
        this.commentPercentage = commentPercentage;
    }

    public double getLinesOfComments() {
        return linesOfComments;
    }

    public void setLinesOfComments(double linesOfComments) {
        this.linesOfComments = linesOfComments;
    }

    public double getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(double linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public double getMajorViolations() {
        return majorViolations;
    }

    public void setMajorViolations(double majorViolations) {
        this.majorViolations = majorViolations;
    }

    public double getMinorViolations() {
        return minorViolations;
    }

    public void setMinorViolations(double minorViolations) {
        this.minorViolations = minorViolations;
    }

    public double getCriticalViolations() {
        return criticalViolations;
    }

    public void setCriticalViolations(double criticalViolations) {
        this.criticalViolations = criticalViolations;
    }

    public double getBlockerViolations() {
        return blockerViolations;
    }

    public void setBlockerViolations(double blockerViolations) {
        this.blockerViolations = blockerViolations;
    }
}
