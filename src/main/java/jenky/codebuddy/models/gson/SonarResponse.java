package jenky.codebuddy.models.gson;

import java.util.List;

/**
 * Created by Fabian on 2-6-2016.
 */
public class SonarResponse {

    private String id;
    private String key;
    private String name;
    private String scope;
    private String qualifier;
    private String date;
    private String creatioDate;
    private String lname;
    private String version;
    private String description;
    private List<Metric> metrics;

    public SonarResponse(){

    }

    @Override
    public String toString() {
        return "Ressources : \n id=" + id  + ",\n key=" + key + ",\n name=" + name + ",\n lname="
                + lname + ",\n scope=" + scope + ",\n qualifier=" + qualifier + ",\n date=" + date
                + ",\n creationDate=" + creatioDate + ",\n version=" + version + "\n description=" + description;
    }
}
