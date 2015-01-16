package epiandroid.epitech.epiandroid.myapplication;

import org.codehaus.jackson.JsonNode;

/**
 * Created by Antony on 13/01/2015.
 */
public class JsonResponce
{
    private static String token;
    private static String ip;
    private static JsonNode infos;
    private static JsonNode history;

    public static void setToken(String _token)
    {
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
        ip = _ip;
    }
    public static JsonNode getInfos()
    {
        return infos;
    }
    public static JsonNode getHistory()
    {
        return history;
    }
    public static void setInfos(JsonNode _infos)
    {
        infos = _infos;
    }
    public static void setHistory(JsonNode _history)
    {
        history = _history;
    }
}
