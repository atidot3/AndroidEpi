package epiandroid.epitech.epiandroid.myapplication;
/**
 * Created by Antony on 13/01/2015.
 */

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import android.util.Log;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

public class JsonParser
{
    private ObjectMapper mapper = null;
    private JsonNode node = null;

    private JsonParser() {
        mapper = new ObjectMapper();
    }

    private static JsonParser INSTANCE = null;

    public static JsonParser getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new JsonParser();
        return INSTANCE;
    }
    public String getIp(String JsonToParse) throws IOException
    {
        node = mapper.readTree(JsonToParse);
        String ip = String.valueOf(node.get("ip"));
        return ip;
    }
    public String getToken(String JsonToParse) throws IOException
    {
        node = mapper.readTree(JsonToParse);
        String token = String.valueOf(node.get("token"));
        return token;
    }
    public JsonNode getInfos(String JsonToParse) throws IOException
    {
        node = mapper.readTree(JsonToParse);
        return node;
    }
    public JsonNode getTree(String JsonToParse) throws IOException {
        JsonNode node = mapper.readTree(JsonToParse);
        return node;
    }
    public static String getParamPicture() throws IOException
    {
        String ok = JsonParser.getInstance().getInfos(JsonResponce.getInfos().toString()).get("infos").get("picture").getTextValue();
        ok = ok.substring(0, ok.length()-3);
        ok += "jpg";
        return ok;
    }
    public static  String getParamLog() throws IOException
    {
        String ok = JsonParser.getInstance().getInfos(JsonResponce.getInfos().toString()).get("current").get("active_log").toString();
        if (ok.equals("null"))
            ok += "";
        return ok;
    }
    public static String getParamMessage() throws IOException
    {
        //String ok = JsonParser.getInstance().getInfos(JsonResponce.getInfos().toString()).get("history").get("title").toString();
        String ok = JsonParser.getInstance().getInfos(JsonResponce.getInfos().toString()).get("history").toString();
        ok = ok.substring(1, ok.length()-1);
        if (ok.equals("null"))
            ok += "";
        return ok;
    }
}
