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
    private String creationDate;
    private String lname;
    private String version;
    private String description;
    private List<Metric> msr;

    public SonarResponse(){

    }

    @Override
    public String toString() {
        return "Ressources : \n id=" + id  + ",\n key=" + key + ",\n name=" + name + ",\n lname="
                + lname + ",\n scope=" + scope + ",\n qualifier=" + qualifier + ",\n date=" + date
                + ",\n creationDate=" + creationDate + ",\n version=" + version + "\n description=" + description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Metric> getMsr() {
        return msr;
    }

    public void setMsr(List<Metric> msr) {
        this.msr = msr;
    }
}
