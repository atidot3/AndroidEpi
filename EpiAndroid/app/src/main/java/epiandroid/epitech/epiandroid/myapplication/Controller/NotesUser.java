package epiandroid.epitech.epiandroid.myapplication.Controller;

/**
 * Created by Antony on 18/01/2015.
 */
public class NotesUser
{
    private String titleModule;
    private String title;
    private String final_note;
    private String comment;

    public NotesUser()
    {
        super();
        this.titleModule = "";
        this.title = "";
        this.final_note = "";
        this.comment = "";
    }
    public NotesUser(String _titleModude, String _title, String _finalNote, String _comment) {
        super();
        this.titleModule = _titleModude;
        this.title = _title;
        this.final_note = _finalNote;
        comment = _comment;
    }
    public String getTitleModule()
    {
        return titleModule;
    }
    public String getTitle()
    {
        return title;
    }
    public String getFinalNotes()
    {
        return final_note;
    }
    public String getComment()
    {
        return comment;
    }
}
