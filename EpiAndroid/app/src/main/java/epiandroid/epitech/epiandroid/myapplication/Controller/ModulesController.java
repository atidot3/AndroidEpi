package epiandroid.epitech.epiandroid.myapplication.Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antony on 19/01/2015.
 */
public class ModulesController
{
    private ArrayList<modulesUser> ModuleList = null;
    private ArrayList<HashMap> ModuleMap = null;
    private HashMap<String, String> map = null;
    private static ModulesController INSTANCE = null;
    private int i = 0;
    public ModulesController()
    {
    }
    public static ModulesController getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ModulesController();
        return INSTANCE;
    }
    public void init(String jsonStringParam) throws IOException
    {
        ModuleMap = new ArrayList<>();
        ModuleList = new ArrayList<modulesUser>();
        try
        {
            JSONObject jsonObject = new JSONObject(jsonStringParam);
            JSONArray histo = jsonObject.getJSONArray("modules");
            while (i < histo.length())
            {
                map = new HashMap<String, String>();
                JSONObject e = histo.getJSONObject(i);
                map.put("title", e.optString("title"));
                map.put("grade", e.optString("grade"));
                map.put("credits", e.optString("credits"));
                map.put("semester", e.optString("semester"));
                map.put("scolaryear", e.optString("scolaryear"));
                ModuleMap.add(map);
                i++;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        i = 0;
        while (i < ModuleMap.size())
        {
            String title = ModuleMap.get(i).get("title").toString();
            String grade = ModuleMap.get(i).get("grade").toString();
            String credits = ModuleMap.get(i).get("credits").toString();
            String semester = ModuleMap.get(i).get("semester").toString();
            String scolaryear = ModuleMap.get(i).get("scolaryear").toString();
            modulesUser hu = new modulesUser(title, grade, credits, semester, scolaryear);
            ModuleList.add(hu);
            i++;
        }
    }
    public ArrayList modulesListeReturn()
    {
        return ModuleList;
    }
}
