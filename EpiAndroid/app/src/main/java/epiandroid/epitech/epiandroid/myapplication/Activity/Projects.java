package epiandroid.epitech.epiandroid.myapplication.Activity;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import epiandroid.epitech.epiandroid.myapplication.Controller.LoginController;
import epiandroid.epitech.epiandroid.myapplication.Controller.projectsController;
import epiandroid.epitech.epiandroid.myapplication.Controller.projectsUser;
import epiandroid.epitech.epiandroid.myapplication.Globals;
import epiandroid.epitech.epiandroid.myapplication.Json.JsonResponce;
import epiandroid.epitech.epiandroid.myapplication.R;


public class Projects extends Fragment
{
    private View rootView;
    private projectsController PC;
    private ArrayList<projectsUser> projectsList = null;
    private ExpandableListView myProj;
    private void doSomeListCheck()
    {
        final String[] title = new String[projectsList.size()];
        final String[][] end = new String[projectsList.size()][1];
        int i = 0;
        while (i < projectsList.size())
        {
            title[i] = projectsList.get(i).getActiTitle();
            end[i][0] = "Fin de l'activitée le: " + projectsList.get(i).EndActi();
            if (title[i].equals("null"))
                title[i] = "";
            if (end[i].equals("null"))
                end[i][0] = "";
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
                return end[groupPosition].length;
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
                tv.setText(end[groupPosition][childPosition]);
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
        myProj.setAdapter(adap);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_projects, container, false);
        projectsActivityFunc();
        return rootView;
    }
    private void projectsActivityFunc()
    {
        LoginController loginController = Globals.loginController;
        PC = projectsController.getInstance();
        myProj = (ExpandableListView) rootView.findViewById(R.id.Projects);
        try
        {
            if ((loginController.sendProjects()) == true)
            {
                PC.init(JsonResponce.getProjects().toString());
                projectsList = PC.projectsListeReturn();
                doSomeListCheck();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}