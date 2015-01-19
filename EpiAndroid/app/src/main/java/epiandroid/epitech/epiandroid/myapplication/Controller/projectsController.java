package epiandroid.epitech.epiandroid.myapplication.Controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antony on 19/01/2015.
 */
public class projectsController
{
    private ArrayList<projectsUser> ProjectsList = null;
    private ArrayList<HashMap> ProjectsMap = null;
    private HashMap<String, String> map = null;
    private static projectsController INSTANCE = null;
    private int i = 0;
    public projectsController()
    {
    }
    public static projectsController getInstance() {
        if (INSTANCE == null)
            INSTANCE = new projectsController();
        return INSTANCE;
    }
    public void init(String jsonStringParam) throws IOException
    {
        ProjectsMap = new ArrayList<>();
        ProjectsList = new ArrayList<projectsUser>();
        try
        {
            JSONObject jsonObject = new JSONObject(jsonStringParam);
            JSONArray histo = jsonObject.getJSONArray("Projects");
            while (i < histo.length())
            {
                map = new HashMap<String, String>();
                JSONObject e = histo.getJSONObject(i);
                map.put("acti_title", e.optString("acti_title"));
                map.put("end_acti", e.optString("end_acti"));
                map.put("registered", e.optString("registered"));
                ProjectsMap.add(map);
                i++;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        i = 0;
        while (i < ProjectsMap.size())
        {
            String acti_title = ProjectsMap.get(i).get("acti_title").toString();
            String end_acti = ProjectsMap.get(i).get("end_acti").toString();
            String registered = ProjectsMap.get(i).get("registered").toString();
            if (registered.equals("1")) {
                projectsUser hu = new projectsUser(acti_title, end_acti, registered);
                ProjectsList.add(hu);
            }
            i++;
        }
    }
    public ArrayList projectsListeReturn()
    {
        return ProjectsList;
    }
}
