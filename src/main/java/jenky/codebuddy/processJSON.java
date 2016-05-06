package jenky.codebuddy;

import org.json.JSONArray;
import org.json.JSONObject;
import wildtornado.scocalc.Calc;
import wildtornado.scocalc.objects.DataInput;
import wildtornado.scocalc.objects.Score;

/**
 * Created by joost on 5-5-2016.
 */

public class processJSON {
    JSONObject metric;
    DataInput metrics;
    Calc calculator;
    private String jsonString;
    private double codeComplexity;
    private double codeDuplicationDensity;
    private double codeViolationsDensity;
    private double numberOfTests;
    private double technicalDebt;
    private double commentPercentage;
    private double linesOfCode;

    /*public static void main(String[] args) {
        httpRequest http = new httpRequest();
        String jsonString = "";
        try{
            jsonString = http.sendPost();
        }
        catch(Exception e){
            System.out.println(e);
        }
        //System.out.println("json = " + json);
        processJSON p = new processJSON();
        //p.readJson(jsonString);
        p.fillMetrics(p.readJson(jsonString));
        DataInput metrics = p.mapData();
        Calc calc = new Calc(metrics);
        System.out.println(" = " + calc.generateScore().getFinalScore());
    }*/

    public Score getScore(){  //this method executes everything in order to retrieve a Score model
        httpRequest http = new httpRequest();
        jsonString = "";
        try{
            jsonString = http.sendPost();
        }
        catch(Exception e){
            System.out.println(e);
        }
        processJSON p = new processJSON();
        p.fillMetrics(p.readJson(jsonString));
        metrics = p.mapData();
        calculator = new Calc(metrics);
        return calculator.generateScore();
    }

    private JSONArray readJson(String jsonString){ //read entire sonarqube api JSON string and select the metrics
        JSONArray json = new JSONArray(jsonString);
        JSONObject obj = json.getJSONObject(0);
        JSONArray metrics = obj.getJSONArray("msr");
        return metrics;
    }

    private void fillMetrics (JSONArray metrics){ //map the metrics from the json to variables
        for(int i = 0; i < metrics.length(); i++){
            metric = metrics.getJSONObject(i);
            switch(metric.getString("key")){ // not all metrics are supported + not yet expendable
                case "ncloc": linesOfCode = metric.getDouble("val"); break;
                case "sqale_index": technicalDebt = metric.getDouble("val"); break;
                case "comment_lines_density": commentPercentage = metric.getDouble("val"); break;
                case "complexity": codeComplexity = metric.getDouble("val"); break;
                case "tests": numberOfTests= metric.getDouble("val"); break;
                case "duplicated_lines_density": codeDuplicationDensity = metric.getDouble("val"); break;
            }
        }
    }

    private DataInput mapData(){ //map the variables to a new datainput object
        DataInput datainput = new DataInput();
        datainput.setCodeComplexity(codeComplexity);
        datainput.setCommentPercentage(commentPercentage);
        datainput.setLinesOfCode(linesOfCode);
        datainput.setTechnicalDebt(technicalDebt);
        datainput.setCodeDuplicationDensity(codeDuplicationDensity);
        datainput.setCodeViolationsDensity(codeViolationsDensity);
        return datainput;
    }
}
