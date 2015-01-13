package epiandroid.epitech.epiandroid.myapplication;

import android.util.Log;

/**
 * Created by Antony on 13/01/2015.
 */
public class JsonResponce
{
    private static String token;
    private static String ip;

    public static void setToken(String _token)
    {
        Log.d("DEBUG", "Stored token:" + _token);
        token = _token;
    }

    public static String getToken()
    {
        return token;
    }

    public static String getIp()
    {
        return ip;
    }

    public static void setIp(String _ip)
    {
        Log.d("DEBUG", "Stored ip:" + _ip);
        ip = _ip;
    }
}
