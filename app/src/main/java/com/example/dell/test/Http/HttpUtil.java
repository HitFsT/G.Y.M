package com.example.dell.test.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import static android.content.ContentValues.TAG;

/**
 * Created by 龙 on 2017/11/11.
 * Handle two methods:
 *      getRequest(): send the getRequest
 *      postRequest(): send the postRequest
 * this class is used to deal with data accessing
 * it should be combined with other activities.
 */

public class HttpUtil {
    public static HttpClient httpClient = new DefaultHttpClient();
    public static final String BASE_URL =
            "http://69.171.68.142:8080/GYM/";

    public static String postRequest(final String url,
         final Map<String, String> rawParams) throws Exception{

        FutureTask<String> task = new FutureTask<String>(
            new Callable<String>(){
                @Override
                public String call() throws Exception{
                    HttpPost post = new HttpPost(url);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    for(String key: rawParams.keySet()){
                        params.add(new BasicNameValuePair(key, rawParams.get(key)));
                    }
                    post.setEntity(new UrlEncodedFormEntity(params));
                    HttpResponse httpResponse;
                    httpResponse = httpClient.execute(post);
                    if(httpResponse.getStatusLine().getStatusCode()==200){
                        String result = EntityUtils.toString(httpResponse.getEntity());
                        return result;
                    }
                    return null;
                }
            });
        new Thread(task).start();
        return task.get();
    }

    /*The get do not require params*/
    public static String getRequest(final String url) throws Exception{
        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>(){
                    @Override
                    public String call() throws Exception{
                        HttpGet get = new HttpGet(url);
                        HttpResponse httpResponse;
                        httpResponse = httpClient.execute(get);
                        if(httpResponse.getStatusLine().getStatusCode()==200){
                            String result = EntityUtils.toString(httpResponse.getEntity(),"ISO-8859-1");
                            return result;
                        }
                        return null;
                    }
                });
        new Thread(task).start();
        return task.get();
    }

    /* Download the picture from the url, the result is Bitmap type*/
    public static Bitmap getpic(String picurl){
        try {
            URL url = new URL(picurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        }catch (Exception e){
            Log.d(TAG,e.getMessage());
        }
        return null;
    }
}
