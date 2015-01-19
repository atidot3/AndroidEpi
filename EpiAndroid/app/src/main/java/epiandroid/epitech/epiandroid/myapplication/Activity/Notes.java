package epiandroid.epitech.epiandroid.myapplication.Activity;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import epiandroid.epitech.epiandroid.myapplication.Controller.LoginController;
import epiandroid.epitech.epiandroid.myapplication.Controller.NotesController;
import epiandroid.epitech.epiandroid.myapplication.Controller.NotesUser;
import epiandroid.epitech.epiandroid.myapplication.Globals;
import epiandroid.epitech.epiandroid.myapplication.Json.JsonResponce;
import epiandroid.epitech.epiandroid.myapplication.R;

public class Notes extends Fragment
{
    private View rootView;
    private ArrayList<NotesUser> notesList = null;
    private ExpandableListView myNotes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        notesActivityFunc();
        return rootView;
    }
    private void doSomeListCheck()
    {
        final String[] title = new String[notesList.size()];
        final String[][] comment = new String[notesList.size()][1];
        int i = 0;
        while (i < notesList.size())
        {
            title[i] = notesList.get(i).getTitle() + ": " + notesList.get(i).getFinalNotes();
            comment[i][0] = notesList.get(i).getComment();
            if (title[i].equals("null"))
                title[i] = "";
            if (comment[i][0].equals("null"))
                comment[i][0] = "";
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
        myNotes.setAdapter(adap);
    }
    private void notesActivityFunc()
    {
        LoginController loginController = Globals.loginController;
        NotesController NC = NotesController.getInstance();
        myNotes = (ExpandableListView) rootView.findViewById(R.id.Notes);
        try
        {
            if ((loginController.sendGrads()) == true)
            {
                NC.init(JsonResponce.getGrads().toString());
                notesList = NC.notesListeReturn();
                doSomeListCheck();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
