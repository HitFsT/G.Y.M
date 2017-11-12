package com.example.dell.test;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

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
            "http://10.0.2.2:8080/GYM/";

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
                    post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
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
}
