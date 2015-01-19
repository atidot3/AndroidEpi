package epiandroid.epitech.epiandroid.myapplication.Controller;

/**
 * Created by Antony on 19/01/2015.
 */
public class projectsUser
{
    private String acti_title;
    private String end_acti;
    private String registered;

    public projectsUser()
    {
        super();
        this.acti_title = "";
        this.end_acti = "";
        this.registered = "";
    }
    public projectsUser(String _titleModude, String _title, String _finalNote) {
        super();
        this.acti_title = _titleModude;
        this.end_acti = _title;
        this.registered = _finalNote;
    }
    public String getActiTitle()
    {
        return acti_title;
    }
    public String EndActi()
    {
        return end_acti;
    }
    public String getRegistered()
    {
        return registered;
    }
}
