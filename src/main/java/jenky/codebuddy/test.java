package jenky.codebuddy;

import org.apache.http.HttpRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import wildtornado.scocalc.Calc;
import wildtornado.scocalc.objects.DataInput;

/**
 * Created by joost on 5-5-2016.
 */

public class test {
    JSONObject metric;
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
        test p = new test();
        //p.readJson(jsonString);
        p.fillMetrics(p.readJson(jsonString));
        DataInput metrics = p.mapData();
        Calc calc = new Calc(metrics);
        System.out.println(" = " + calc.generateScore().getFinalScore());
    }*/

    private JSONArray readJson(String jsonString){
        JSONArray json = new JSONArray(jsonString);
        JSONObject obj = json.getJSONObject(0);
        JSONArray metrics = obj.getJSONArray("msr");
        return metrics;
    }
    private void fillMetrics (JSONArray metrics){
        for(int i = 0; i < metrics.length(); i++){
            metric = metrics.getJSONObject(i);
            switch(metric.getString("key")){
                case "ncloc": linesOfCode = metric.getDouble("val"); break;
                case "sqale_index": technicalDebt = metric.getDouble("val"); break;
                case "comment_lines_density": commentPercentage = metric.getDouble("val"); break;
                case "complexity": codeComplexity = metric.getDouble("val"); break;
                case "tests": numberOfTests= metric.getDouble("val"); break;
                case "duplicated_lines_density": codeDuplicationDensity = metric.getDouble("val"); break;
            }
        }
    }

    private DataInput mapData(){
        DataInput datainput = new DataInput();
        /*datainput.setCodeComplexity(codeComplexity);
        datainput.setCommentPercentage(commentPercentage);
        datainput.setLinesOfCode(linesOfCode);
        datainput.setTechnicalDebt(technicalDebt);
        datainput.setCodeDuplicationDensity(codeDuplicationDensity);
        datainput.setCodeViolationsDensity(codeViolationsDensity);*/
        return datainput;
    }
}
