package epiandroid.epitech.epiandroid.myapplication;

/**
 * Created by Antony on 13/01/2015.
 */
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InformationsController
{
    private String URL_INFOS = "https://epitech-api.herokuapp.com/infos";
    private HttpClient client = null;
    private HttpPost post = null;
    private List<NameValuePair> pairs = null;
    public InformationsController()
    {
        client = new DefaultHttpClient();
        post = new HttpPost(URL_INFOS);
    }
    public boolean send(String token) throws IOException
    {
        Log.d("DEBUG", token);
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("token", token));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        Log.d("DEBUG", jsonString);
        String Response = JsonParser.getInstance().getIp(jsonString);
        Log.d("DEBUG", Response);
        if (Response.equals("null"))
            return false;
        JsonResponce.setIp(Response);
        Log.d("DEBUG", Response);
        return true;
    }
}
