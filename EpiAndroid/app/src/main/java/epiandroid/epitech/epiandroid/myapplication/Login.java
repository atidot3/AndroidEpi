package epiandroid.epitech.epiandroid.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Login extends ActionBarActivity {
    private EditText EmailText;
    private EditText PasswordText;
    private TextView Error;
    private LoginController loginController;
    private InformationsController IFController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        loginController = new LoginController();
        setContentView(R.layout.activity_login);
        EmailText = (EditText) findViewById(R.id.editText);
        PasswordText = (EditText) findViewById(R.id.editText2);
        Error = (TextView) findViewById(R.id.textView);
        Button LoginButton = (Button) findViewById(R.id.button);
        LoginButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ((isEmpty(EmailText) == false) && (isEmpty(PasswordText) == false))
                {
                    try
                    {
                        if ((loginController.send(EmailText.getText().toString(), PasswordText.getText().toString())) == true)
                        {
                            IFController = new InformationsController();
                            if ((IFController.send(JsonResponce.getToken())) == true)
                                Error.setText(JsonResponce.getIp());
                            else
                                Error.setText("Connection token is invalid or has expired");
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Error.setText("String must be not null");
                    Log.d("DEBUG", "String must be not null");
                }
            }
        });
    }
    private HttpClient createHttpClient()
    {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);

        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);

        return new DefaultHttpClient(conMgr, params);
    }
    private boolean isEmpty(EditText etText)
    {
        if (etText.getText().toString().trim().length() > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
