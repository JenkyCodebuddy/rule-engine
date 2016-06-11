package jenky.codebuddy.models.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fabian on 3-6-2016.
 */
public class Metric {

    @SerializedName("key")
    private String key;

    @SerializedName("val")
    private Double val;

    @SerializedName("frmt_val")
    private String formattedValue;

    public Metric(){

    }

    @Override
    public String toString() {
        return " \n key=" + key  + ",\n val=" + val + ",\n formattedValue=" + formattedValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public void setFormattedValue(String formattedValue) {
        this.formattedValue = formattedValue;
    }
}
