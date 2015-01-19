package epiandroid.epitech.epiandroid.myapplication.Controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antony on 18/01/2015.
 */
public class NotesController
{
    private ArrayList<NotesUser> NotesList = null;
    private ArrayList<HashMap> NotesMap = null;
    private HashMap<String, String> map = null;
    private static NotesController INSTANCE = null;
    private int i = 0;
    public NotesController()
    {
    }
    public static NotesController getInstance() {
        if (INSTANCE == null)
            INSTANCE = new NotesController();
        return INSTANCE;
    }
    public void init(String jsonStringParam) throws IOException
    {
        NotesMap = new ArrayList<>();
        NotesList = new ArrayList<NotesUser>();
        try
        {
            JSONObject jsonObject = new JSONObject(jsonStringParam);
            JSONArray histo = jsonObject.getJSONArray("notes");
            while (i < histo.length())
            {
                map = new HashMap<String, String>();
                JSONObject e = histo.getJSONObject(i);
                map.put("titlemodule", e.optString("titlemodule"));
                map.put("title", e.optString("title"));
                map.put("final_note", e.optString("final_note"));
                map.put("comment", e.optString("comment"));
                NotesMap.add(map);
                i++;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        i = 0;
        while (i < NotesMap.size())
        {
            String titlemodule = NotesMap.get(i).get("titlemodule").toString();
            String title = NotesMap.get(i).get("title").toString();
            String final_note = NotesMap.get(i).get("final_note").toString();
            String comment = NotesMap.get(i).get("comment").toString();
            NotesUser hu = new NotesUser(titlemodule,title,final_note,comment);
            NotesList.add(hu);
            i++;
        }
    }
    public ArrayList notesListeReturn()
    {
        return NotesList;
    }
}
