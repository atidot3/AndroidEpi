package epiandroid.epitech.epiandroid.myapplication.Controller;

/**
 * Created by Antony on 13/01/2015.
 */
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.JsonNode;

import epiandroid.epitech.epiandroid.myapplication.Json.JsonParser;
import epiandroid.epitech.epiandroid.myapplication.Json.JsonResponce;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

public class LoginController
{
    private String URL_LOGIN = "https://epitech-api.herokuapp.com/login";
    private String URL_MODULES = "https://epitech-api.herokuapp.com/modules";
    private String URL_PROJECTS = "https://epitech-api.herokuapp.com/projects";
    private String URL_GRADS = "https://epitech-api.herokuapp.com/marks";
    private String URL_PICTURE = "https://epitech-api.herokuapp.com/photo";
    private String URL_ALERTS = "https://epitech-api.herokuapp.com/alerts";
    private String URL_INFOS = "https://epitech-api.herokuapp.com/infos";
    private String URL_PLANNING = "https://epitech-api.herokuapp.com/planning";
    private HttpClient client = null;
    private HttpPost post = null;
    private List<NameValuePair> pairs = null;

    public LoginController()
    {
        client = new DefaultHttpClient();
    }
    public boolean send(String email, String password) throws IOException
    {
        post = new HttpPost(URL_LOGIN);
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("login", email));
        pairs.add(new BasicNameValuePair("password", password));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        String token = JsonParser.getInstance().getToken(jsonString);
        if (token.equals("null"))
            return false;
        JsonResponce.setToken(token.substring(1, token.length() - 1));
        JsonResponce.setLogin(email);
        return true;
    }
    public boolean sendInfos() throws IOException
    {
        post = new HttpPost(URL_INFOS);
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("token", JsonResponce.getToken()));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        JsonNode Infos = JsonParser.getInstance().getInfos(jsonString);
        if (Infos.isNull())
            return false;
        JsonResponce.setInfos(Infos);
        return true;
    }
    public boolean sendAlerts() throws IOException
    {
        post = new HttpPost(URL_ALERTS);
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("token", JsonResponce.getToken()));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        JsonNode alerts = JsonParser.getInstance().getInfos(jsonString);
        if (alerts.isNull())
            return false;
        JsonResponce.setAlerts(alerts);
        return true;
    }
    public static void longInfo(String str) {
        if(str.length() > 4000) {
            Log.i("anto", str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.i("anto", str);
    }
    public boolean sendPicture() throws IOException {
        post = new HttpPost(URL_PICTURE);
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("token", JsonResponce.getToken()));
        pairs.add(new BasicNameValuePair("login", JsonResponce.getLogin()));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        String picture = JsonParser.getInstance().getPicture(jsonString);
        if (picture.equals("null"))
            return false;
        JsonResponce.setPicture(picture.substring(1, picture.length() - 1));
        return true;
    }
    public boolean sendGrads() throws IOException {
        post = new HttpPost(URL_GRADS);
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("token", JsonResponce.getToken()));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        jsonString = jsonString.replace("notes:", "\"notes\":");
        jsonString = jsonString.substring(0, jsonString.length());
        JsonNode grads = JsonParser.getInstance().getInfos(jsonString);
        if (grads.isNull())
            return false;
        JsonResponce.setGrads(grads);
        return true;
    }
    public boolean sendPlanning(String... args) throws IOException
    {
        post = new HttpPost(URL_PLANNING);
        pairs.add(new BasicNameValuePair("token", JsonResponce.getToken()));
        pairs.add(new BasicNameValuePair("start", args[0]));
        pairs.add(new BasicNameValuePair("end", args[1]));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        JsonNode planning = JsonParser.getInstance().getInfos(jsonString);
        if (planning.isNull())
            return false;
        JsonResponce.setPlanning(planning);
        return true;
    }
    public boolean sendProjects() throws IOException
    {
        post = new HttpPost(URL_PROJECTS);
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("token", JsonResponce.getToken()));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        String myNewjsonString = "{\"Projects\":";
        myNewjsonString = myNewjsonString.concat(jsonString + "}");
        JsonNode projects = JsonParser.getInstance().getInfos(myNewjsonString);
        if (projects.isNull())
            return false;
        JsonResponce.setProjects(projects);
        return true;
    }
    public boolean sendModules() throws IOException
    {
        post = new HttpPost(URL_MODULES);
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("token", JsonResponce.getToken()));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        jsonString = jsonString.replace("modules:", "\"modules\":");
        jsonString = jsonString.substring(0, jsonString.length());
        JsonNode modules = JsonParser.getInstance().getInfos(jsonString);
        if (modules.isNull())
            return false;
        JsonResponce.setModules(modules);
        return true;
    }
}