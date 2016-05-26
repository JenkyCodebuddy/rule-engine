package jenky.codebuddy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joost on 26-5-2016.
 */
public class BusinessLogicController {

    public BusinessLogicController(){

    }

    public Map<String, String> createGithubUserInfoMap(Map<String, String> headers){
        Map githubInfoMap = new HashMap<String, String>();
        githubInfoMap.put("username",headers.get("username"));
        githubInfoMap.put("email", headers.get("email"));
        githubInfoMap.put("branch", headers.get("branch"));
        githubInfoMap.put("sha", headers.get("sha"));
        githubInfoMap.put("projectName", headers.get("projectname"));
        return githubInfoMap;
    }
}
