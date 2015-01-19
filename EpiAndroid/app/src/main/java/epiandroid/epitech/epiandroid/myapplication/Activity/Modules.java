package epiandroid.epitech.epiandroid.myapplication.Activity;

import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import epiandroid.epitech.epiandroid.myapplication.Controller.LoginController;
import epiandroid.epitech.epiandroid.myapplication.Controller.ModulesController;
import epiandroid.epitech.epiandroid.myapplication.Controller.modulesUser;
import epiandroid.epitech.epiandroid.myapplication.Globals;
import epiandroid.epitech.epiandroid.myapplication.Json.JsonResponce;
import epiandroid.epitech.epiandroid.myapplication.R;


public class Modules extends Fragment
{
    private View rootView;
    private ModulesController MC;
    private ArrayList<modulesUser> modulesList = null;
    private ExpandableListView myModules;

    private void doSomeListCheck()
    {
        final String[] title = new String[modulesList.size()];
        final String[][] comment = new String[modulesList.size()][4];
        int i = 0;
        while (i < modulesList.size())
        {
            title[i] = modulesList.get(i).getTitleModule();
            comment[i][0] = "Année: " + modulesList.get(i).getScolarYear();
            comment[i][1] = "Semestre: " + modulesList.get(i).getSemester();
            comment[i][2] = "Crédit: " + modulesList.get(i).getCredit();
            comment[i][3] = "Grade: " + modulesList.get(i).getGrade();
            if (title[i].equals("null"))
                title[i] = "";
            if (comment[i][0].equals("null") || comment[i][1].equals("null") || comment[i][2].equals("null") || comment[i][3].equals("null")) {
                comment[i][0] = "";
                comment[i][1] = "";
                comment[i][2] = "";
                comment[i][3] = "";
            }
            i++;
        }
        ExpandableListAdapter adap = new ExpandableListAdapter() {
            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }
            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }
            @Override
            public int getGroupCount() {
                return title.length;
            }
            @Override
            public int getChildrenCount(int groupPosition) {
                return comment[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return groupPosition;
            }
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return null;
            }
            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }
            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return 0;
            }
            @Override
            public boolean hasStableIds() {
                return false;
            }
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                TextView tv = new TextView(getActivity());
                tv.setText(title[groupPosition]);
                return tv;
            }
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView tv = new TextView(getActivity());
                tv.setText(comment[groupPosition][childPosition]);
                return tv;
            }
            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }
            @Override
            public boolean isEmpty() {
                return false;
            }
            @Override
            public void onGroupExpanded(int groupPosition) {

            }
            @Override
            public void onGroupCollapsed(int groupPosition) {

            }
            @Override
            public long getCombinedChildId(long groupId, long childId) {
                return 0;
            }
            @Override
            public long getCombinedGroupId(long groupId) {
                return 0;
            }
        };
        myModules.setAdapter(adap);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_modules, container, false);
        modulesActivityFunc();
        return rootView;
    }
    private void modulesActivityFunc()
    {
        LoginController loginController = Globals.loginController;
        MC = ModulesController.getInstance();
        myModules = (ExpandableListView) rootView.findViewById(R.id.modules);
        try
        {
            if ((loginController.sendModules()) == true)
            {
                MC.init(JsonResponce.getModules().toString());
                modulesList = MC.modulesListeReturn();
                doSomeListCheck();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
