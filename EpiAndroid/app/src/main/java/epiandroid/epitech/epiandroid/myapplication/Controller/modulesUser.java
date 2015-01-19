package epiandroid.epitech.epiandroid.myapplication.Controller;

/**
 * Created by Antony on 19/01/2015.
 */
public class modulesUser
{
    private String title;
    private String grade;
    private String credits;
    private String semester;
    private String scolaryear;
    public modulesUser()
    {
        super();
        this.title = "";
        this.grade = "";
        this.credits = "";
        this.semester = "";
        this.scolaryear = "";
    }
    public modulesUser(String _title, String _grade, String _credits, String _semester, String _scolaryear)
    {
        super();
        this.title = _title;
        this.grade = _grade;
        this.credits = _credits;
        this.semester = _semester;
        this.scolaryear = _scolaryear;
    }
    public String getTitleModule()
    {
        return title;
    }
    public String getGrade()
    {
        return grade;
    }
    public String getCredit()
    {
        return credits;
    }
    public String getSemester()
    {
        return semester;
    }
    public String getScolarYear(){return scolaryear;}
}
