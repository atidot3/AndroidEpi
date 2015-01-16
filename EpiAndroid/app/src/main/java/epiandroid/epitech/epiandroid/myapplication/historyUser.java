package epiandroid.epitech.epiandroid.myapplication;

/**
 * Created by Antony on 16/01/2015.
 */
import android.util.Log;

public class historyUser
{
    private String title;
    private String content;
    private String id;
    private String visible;
    private String id_activite;
    private String _class;

    public historyUser()
    {
        super();
        this.title = "";
        this.content = "";
        this.id = "";
        this.visible = "";
        this.id_activite = "";
        this._class = "";
    }
    public historyUser(String _title, String _content, String _id,
                String _visible, String _id_activite, String _Class) {
        super();
        this.title = _title;
        this.content = _content;
        this.id = _id;
        this.visible = _visible;
        this.id_activite = _id_activite;
        this._class = _Class;
    }
    public String getTitle() {
        return title;
    }
    public String getContent()
    {
        return content;
    }
    public String getId()
    {
        return id;
    }
    public String getIdActivite()
    {
        return id_activite;
    }
    public String getClassHistory()
    {
        return _class;
    }
    public void setTitle(String _title)
    {
        title = _title;
    }
    public void setContent(String _title)
    {
        content = _title;
    }
    public void setId(String _title)
    {
        id = _title;
    }
    public void setVisible(String _title)
    {
        visible = _title;
    }
    public void setIdActive(String _title)
    {
        id_activite = _title;
    }
    public void setClassHistory(String _title)
    {
        _class = _title;
    }
}