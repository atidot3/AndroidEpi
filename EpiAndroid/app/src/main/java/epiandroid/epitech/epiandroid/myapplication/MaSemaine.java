package epiandroid.epitech.epiandroid.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MaSemaine extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_ma_semaine, container, false);
        return rootView;
    }
}
