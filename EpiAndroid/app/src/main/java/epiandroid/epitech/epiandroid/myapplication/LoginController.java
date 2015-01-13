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

public class LoginController
{
    private String URL_LOGIN = "https://epitech-api.herokuapp.com/login";
    private HttpClient client = null;
    private HttpPost post = null;
    private List<NameValuePair> pairs = null;

    public LoginController()
    {
        client = new DefaultHttpClient();
        post = new HttpPost(URL_LOGIN);
    }
    public boolean send(String email, String password) throws IOException
    {
        pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("login", email));
        pairs.add(new BasicNameValuePair("password", password));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);
        String jsonString = EntityUtils.toString(response.getEntity());
        Log.d("DEBUG", jsonString);
        String token = JsonParser.getInstance().getToken(jsonString);
        if (token.equals("null"))
            return false;
        JsonResponce.setToken(token);
        return true;
    }
}
