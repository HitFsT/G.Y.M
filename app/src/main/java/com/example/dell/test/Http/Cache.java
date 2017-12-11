package com.example.dell.test.Http;

import android.content.Context;

import org.json.JSONArray;

/**
 * Created by 龙 on 2017/12/11.
 */

public class Cache {

    public static JSONArray cacheGymlist(Context context){
        JSONArray gyms = new JSONArray();
        if(RefreshORM.get(context,"gym")>=0){
            if(RefreshORM.get(context,"gym") == 1){
                try{
                    gyms = getGymlist();
                    GymORM.insertGyms(context,gyms);
                    RefreshORM.setfalse(context,"gym");
                }catch(Exception e){
                    DialogUtil.showDialog(context, e.getMessage());
                }
            }else{
                DialogUtil.showDialog(context, "使用缓存");
                gyms = GymORM.getGyms(context);
            }
        }else{
            DialogUtil.showDialog(context, "缓存出错");
        }
        return gyms;
    }

    private static JSONArray getGymlist() throws Exception{
        String url = HttpUtil.BASE_URL + "GymList";
        return new JSONArray(HttpUtil.getRequest(url));
    }
}
