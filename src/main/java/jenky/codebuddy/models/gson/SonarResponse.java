package jenky.codebuddy.models.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SonarResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    @SerializedName("scope")
    private String scope;

    @SerializedName("qualifier")
    private String qualifier;

    @SerializedName("date")
    private String date;

    @SerializedName("creationDate")
    private String creationDate;

    @SerializedName("lname")
    private String lName;

    @SerializedName("version")
    private String version;

    @SerializedName("description")
    private String description;

    @SerializedName("msr")
    private List<Metric> msr;

    public SonarResponse(){

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

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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
