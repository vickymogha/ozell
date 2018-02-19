package com.ozellcooner.webserive;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Admin on 8/11/2017.
 */

public class WebService {

    //public static String URL = "http://103.18.72.141:86/api/User";
   // public static String MAIN_URL = "http://192.168.0.16/";
    //http://103.18.72.141:85/api/user
    public static String URL = "http://192.168.0.154:8086/CRM1";
    public static String getImageUploadDataMethod = "getImageUploadData";
    public static String getColorTrendsMethod = "getColorTrends";
    //getListOfAllTypeColor
    public static String getListOfAllTypeColorMethod = "getColorJSON";



    public static String getImageUploadDataMethodURL(String type) {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.addSubfolder(getImageUploadDataMethod);
        urlBuilder.addParameter("tabType", type);
        return urlCrearter(urlBuilder);
    }
    public static String getColorTrendsUrl() {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.addSubfolder(getColorTrendsMethod);
        return urlCrearter(urlBuilder);
    }

    public static String getListOfAllTypeColorUrl() {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.addSubfolder(getListOfAllTypeColorMethod);
        return urlCrearter(urlBuilder);
    }







    static String urlCrearter(URLBuilder urlBuilder) {
        String finalURL = "";
        try {
            finalURL = urlBuilder.getRelativeURL(URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return finalURL;
    }


    static class URLBuilder {
        private StringBuilder folders, params;
        private String connType, host;

        void setConnectionType(String conn) {
            connType = conn;
        }

        URLBuilder() {
            folders = new StringBuilder();
            params = new StringBuilder();
        }

        URLBuilder(String host) {
            this();
            this.host = host;
        }

        void addSubfolder(String folder) {
            folders.append("/");
            folders.append(folder);
        }

        void addParameter(String parameter, String value) {
            if (params.toString().length() > 0) {
                params.append("&");
            }
            params.append(parameter);
            params.append("=");
            params.append(value);
        }

        String getURL() throws URISyntaxException, MalformedURLException {
            URI uri = new URI(connType, host, folders.toString(),
                    params.toString(), null);
            return uri.toURL().toString();
        }

        String getRelativeURL() throws URISyntaxException, MalformedURLException {
            URI uri = new URI(null, null, folders.toString(), params.toString(), null);
            return uri.toString();
        }

        String getRelativeURL(String baseURL) throws URISyntaxException, MalformedURLException {
            URI uri = new URI(null, null, folders.toString(), params.toString(), null);
            return baseURL + uri.toString();
        }
    }


}
