package epiandroid.epitech.epiandroid.myapplication.Json;
/**
 * Created by Antony on 13/01/2015.
 */

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;

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
    public String getPicture(String JsonToParse) throws IOException
    {
        node = mapper.readTree(JsonToParse);
        String url = String.valueOf(node.get("url"));
        return url;
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
    public static  String getParamLog() throws IOException
    {
        String ok = JsonParser.getInstance().getInfos(JsonResponce.getInfos().toString()).get("current").get("active_log").toString();
        if (ok.equals("null"))
            ok += "";
        return ok;
    }
}
