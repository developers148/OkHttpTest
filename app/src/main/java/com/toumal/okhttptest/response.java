package com.toumal.okhttptest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class response {
    HashMap<String,HashMap<String,String>> mymap;

    public response(String url, Context context){



        OkHttpClient  okHttpClient =new OkHttpClient.Builder().build();

        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("not response","e");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

//                in here initialize it
                mymap=new HashMap<>();

                // api collect from main activity

                String data = response.body().string();

                Log.e("Server Response",data);

                try{
                    //collect data from as json object

                    JSONObject jsonObject = new JSONObject(data);
                    Log.e("page",jsonObject.getString("page"));
                    Log.e("per_page",jsonObject.getString("per_page"));
                    Log.e("total",jsonObject.getString("total"));
                    Log.e("total_pages",jsonObject.getString("total_pages"));
                    Log.e("data",jsonObject.getString("data"));


// take a json array into a json object

                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject tempjson=jsonArray.getJSONObject(i);
                        HashMap<String,String> tempmap=new HashMap<>();
                        tempmap.put("id",tempjson.getString("id"));
                        tempmap.put("name",tempjson.getString("name"));
                        tempmap.put("year",tempjson.getString("year"));
                        tempmap.put("color",tempjson.getString("color"));
                        tempmap.put("pantone_value",tempjson.getString("pantone_value"));
                        mymap.put(String.valueOf(i),tempmap);
                    }
                    for(int j=0;j<mymap.size();j++){
//                        HashMap<String,String> mydata=mymap.get(String.valueOf(3));

                        HashMap<String,String> mydata=mymap.get(String.valueOf(j));

                        Log.e("year",mydata.get("year"));
                    }
                   // JSONArray jsonArray = jsonObject.getJSONArray("data");
                }catch (JSONException e){
                    e.printStackTrace();
                }



//                ((Activity)context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        MainActivity.serverresponse= mymap.get("email");
//                        MainActivity.fun();
//                    }
//                });





            }
        });

    }

}
