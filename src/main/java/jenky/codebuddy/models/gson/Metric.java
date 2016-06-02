package jenky.codebuddy.models.gson;

/**
 * Created by Fabian on 3-6-2016.
 */
public class Metric {

    private String key;
    private Float val;
    private String frmt_val;

    public Metric(){

    }

    @Override
    public String toString() {
        return " \n key=" + key  + ",\n val=" + val + ",\n frmt_val=" + frmt_val;
    }

}
