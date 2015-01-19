package epiandroid.epitech.epiandroid.myapplication.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import epiandroid.epitech.epiandroid.myapplication.Controller.LoginController;
import epiandroid.epitech.epiandroid.myapplication.Globals;
import epiandroid.epitech.epiandroid.myapplication.R;

public class MaSemaine extends Fragment
{
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_ma_semaine, container, false);
        planningActivityFunc();
        return rootView;
    }
    private void planningActivityFunc()
    {
        LoginController loginController = Globals.loginController;
        Date now = new Date();
        Date oneYear;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.YEAR, 1);
        oneYear = c.getTime();
        try
        {
            if ((loginController.sendPlanning(dateFormat.format(now), dateFormat.format(oneYear))) == true)
            {
                Log.d("anto", "planning ok");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
