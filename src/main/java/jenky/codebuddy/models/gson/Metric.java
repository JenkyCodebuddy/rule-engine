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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Float getVal() {
        return val;
    }

    public void setVal(Float val) {
        this.val = val;
    }

    public String getFrmt_val() {
        return frmt_val;
    }

    public void setFrmt_val(String frmt_val) {
        this.frmt_val = frmt_val;
    }
}
