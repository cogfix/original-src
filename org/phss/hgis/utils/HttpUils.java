package org.phss.hgis.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpUils {
    InputStream content;
    public String err;
    HgisDBUtils hgisDb;
    Context httpContecx;
    SharedPreferences httpPref;
    private String httpResponseString;
    HttpClient httpclient;
    HttpGet httpget;
    HttpPost httppost;

    public HttpUils() {
        this.content = null;
        this.err = "";
        this.httpResponseString = "";
    }

    public HttpUils(String url, Context context) {
        this.content = null;
        this.err = "";
        this.httpResponseString = "";
        this.httpclient = new DefaultHttpClient();
        this.httppost = new HttpPost(url);
        this.httpContecx = context;
    }

    public boolean dataPost(Map<String, String> datam) {
        try {
            List<NameValuePair> nameValuePairs = new ArrayList(34);
            for (Entry<String, String> entry : datam.entrySet()) {
                String col = (String) entry.getKey();
                if (!(col.equals("issynced") || col.equals("_id"))) {
                    nameValuePairs.add(new BasicNameValuePair(col, (String) entry.getValue()));
                }
            }
            this.httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = this.httpclient.execute(this.httppost);
            if (response.getStatusLine().getStatusCode() != 200) {
                return false;
            }
            this.content = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.content));
            StringBuilder sb = new StringBuilder();
            String str = "";
            while (true) {
                str = reader.readLine();
                if (str == null) {
                    break;
                }
                sb.append(str);
            }
            this.content.close();
            String strContent = sb.toString().trim();
            this.httpResponseString = strContent;
            if (strContent.equals("success")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            this.err = e.getMessage();
            return false;
        }
    }

    public String getHttpResponseString() {
        return this.httpResponseString;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) this.httpContecx.getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
