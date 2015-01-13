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

public class JsonParser
{
    private ObjectMapper mapper = null;
    private JsonNode node = null;
    private JsonFactory jsonFactory = null;


    private JsonParser() {
        mapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }

    private static JsonParser INSTANCE = null;

    public static JsonParser getInstance() {
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
}
