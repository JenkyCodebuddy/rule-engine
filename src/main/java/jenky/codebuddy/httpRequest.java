package jenky.codebuddy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;

public class httpRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    String username = "admin";
    String password = "CC4d96";

    public String sendPost() throws Exception {

        //String url = "http://localhost:9000/api/resources?resource=com.example%3AINFMAN01-1&metrics=complexity,duplicated_lines_density,violations_density,tests,sqale_index,comment_lines_density,ncloc&format=json"; //url which calls required metrics
        String url = "http://145.24.222.226/sonar/api/resources?resource=jenky:codebuddy.rule-engine&metrics=complexity,duplicated_lines_density,violations_density,tests,sqale_index,comment_lines_density,ncloc&format=json";
        CredentialsProvider cred = new BasicCredentialsProvider();
        cred.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultCredentialsProvider(cred)
                .build();
        HttpPost post = new HttpPost(url);


        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
        urlParameters.add(new BasicNameValuePair("cn", ""));
        urlParameters.add(new BasicNameValuePair("locale", ""));
        urlParameters.add(new BasicNameValuePair("caller", ""));
        urlParameters.add(new BasicNameValuePair("num", "12345"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }

}