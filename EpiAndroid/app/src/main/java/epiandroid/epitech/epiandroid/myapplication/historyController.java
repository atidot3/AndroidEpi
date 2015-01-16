package epiandroid.epitech.epiandroid.myapplication;

/**
 * Created by Antony on 16/01/2015.
 */

import android.util.Log;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Iterator;

public class historyController
{
    private ArrayList<historyUser> historyList = null;
    private ArrayList<HashMap> historyMap = null;
    private HashMap<String, String> map = null;
    private static historyController INSTANCE = null;
    private int i = 0;

    public historyController()
    {
    }
    public static historyController getInstance() {
        if (INSTANCE == null)
            INSTANCE = new historyController();
        return INSTANCE;
    }
    public void init(String jsonStringParam) throws IOException
    {
        historyMap = new ArrayList<>();
        historyList = new ArrayList<historyUser>();
        try
        {
            JSONObject jsonObject = new JSONObject(jsonStringParam);
            JSONArray  histo = jsonObject.getJSONArray("history");
            while (i <= histo.length())
            {
                map = new HashMap<String, String>();
                JSONObject e = histo.getJSONObject(i);
                map.put("title", e.optString("title"));
                map.put("content", e.optString("content"));
                map.put("id", e.optString("id"));
                map.put("visible", e.optString("visible"));
                map.put("id_activite", e.optString("id_activite"));
                map.put("_class", e.optString("class"));
                historyMap.add(map);
                i++;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        i = 0;
        while (i < historyMap.size())
        {
            String title = historyMap.get(i).get("title").toString();
            String content = historyMap.get(i).get("content").toString();
            String id = historyMap.get(i).get("id").toString();
            String visible = historyMap.get(i).get("visible").toString();
            String id_activite = historyMap.get(i).get("id_activite").toString();
            String _class = historyMap.get(i).get("_class").toString();
            historyUser hu = new historyUser(
                    title,
                    content,
                    id,
                    visible,
                    id_activite,
                    _class);
            historyList.add(hu);
            i++;
        }
    }
    public ArrayList historyListeReturn()
    {
        return historyList;
    }
}
