package epiandroid.epitech.epiandroid.myapplication.Json;

import org.codehaus.jackson.JsonNode;

/**
 * Created by Antony on 13/01/2015.
 */
public class JsonResponce
{
    private static String token;
    private static String ip;
    private static JsonNode infos;
    private static JsonNode alerts;
    private static JsonNode grads;
    private static String login;
    private static String picture;
    private static JsonNode planning;
    private static JsonNode projects;
    private static JsonNode modules;

    public static void setToken(String _token)
    {
        token = _token;
    }
    public static void setLogin(String _login)
    {
        login = _login;
    }
    public static String getLogin()
    {
        return login;
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
    public static void setInfos(JsonNode _infos)
    {
        infos = _infos;
    }
    public static void setAlerts(JsonNode _alerts) {
        alerts = _alerts;
    }
    public static JsonNode getAlerts() {
        return alerts;
    }
    public static JsonNode getGrads() {
        return grads;
    }
    public static void setGrads(JsonNode _grads) {
        grads = _grads;
    }
    public static void setPicture(String _picture)
    {
        picture = _picture;
    }
    public static String getPicture()
    {
        return picture;
    }
    public static void setPlanning(JsonNode _planning)
    {
        planning = _planning;
    }
    public static JsonNode getPlanning()
    {
        return planning;
    }
    public static void setProjects(JsonNode _projects)
    {
        projects = _projects;
    }
    public static JsonNode getProjects()
    {
        return projects;
    }
    public static void setModules(JsonNode _modules) {
        modules = _modules;
    }
    public static JsonNode getModules()
    {
        return modules;
    }
}