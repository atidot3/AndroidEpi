package epiandroid.epitech.epiandroid.myapplication;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static epiandroid.epitech.epiandroid.myapplication.R.id.LastMessage;

public class InfosActivity extends Fragment
{
    private ImageView PictureProfile;
    private ImageView PictureLog;
    private TextView log;
    private Bitmap bitmap;
    private View rootView;
    private ListView myMessages;
    private historyController HC;
    private ArrayList<historyUser> historyList = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        InfosActivityFunc();
        return rootView;
    }
    private void doSomeListCheck()
    {
        String[] title = new String[historyList.size()];
        ArrayAdapter<String> adapter;
        int i = 0;
        while (i < historyList.size())
        {
            title[i] = Html.fromHtml(historyList.get(i).getTitle()).toString();
            i++;
        }
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, title);
        myMessages.setAdapter(adapter);
    }
    private void InfosActivityFunc()
    {
        PictureProfile = (ImageView) rootView.findViewById(R.id.ProfilPicture);
        PictureLog = (ImageView) rootView.findViewById(R.id.FeuLog);
        log = (TextView) rootView.findViewById(R.id.logTime);
        myMessages = (ListView) rootView.findViewById(LastMessage);
        LoginController loginController = Globals.loginController;
        HC = historyController.getInstance();
        try
        {
            if ((loginController.sendInfos()) == true)
            {
                bitmap = getImageFromURL("https://cdn.local.epitech.eu/userprofil/profilview/" + JsonParser.getParamPicture());
                log.setText(JsonParser.getParamLog());
                HC.init(JsonResponce.getInfos().toString());
                historyList = HC.historyListeReturn();
                doSomeListCheck();
                PictureProfile.setImageBitmap(bitmap);
                PictureLog.setImageResource(R.drawable.ic_red);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public Bitmap getImageFromURL(String url)
    {
        try
        {
            URL _url = new URL(url);
            HttpURLConnection co = (HttpURLConnection) _url.openConnection();
            co.setDoInput(true);
            co.connect();
            InputStream input = co.getInputStream();
            Bitmap myBit = BitmapFactory.decodeStream(input);
            return myBit;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
