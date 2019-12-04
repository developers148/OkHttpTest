package com.toumal.okhttptest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    public static HashMap<String,HashMap<String,String>> serverresponse;

    static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mytext =findViewById(R.id.textEmail);
        listView=findViewById(R.id.list_view);

        new response("https://reqres.in/api/unknown",MainActivity.this);



    }

    public static void fun(HashMap<String,HashMap<String,String>> serverresponse,Context context){
            MainActivity.serverresponse = serverresponse;
            Log.e("hashmap",serverresponse.toString());

            ArrayList<Modeldata> mylist=new ArrayList<>();


            for(int i=0;i<serverresponse.size();i++){
                mylist.add(new Modeldata(serverresponse.get(String.valueOf(i)).get("name"),serverresponse.get(String.valueOf(i)).get("year"),serverresponse.get(String.valueOf(i)).get("color")));
            }


            Adapter adapter=new Adapter(context,mylist);
            listView.setAdapter(adapter);




    }


    static class Modeldata{
        String name,year,color;
        Modeldata(String name,String year,String color){
            this.name=name;
            this.year=year;
            this.color=color;
        }
    }

    static class Adapter extends ArrayAdapter<Modeldata>{

        ArrayList<Modeldata> objects;
        Context context;

        public Adapter(@NonNull Context context, ArrayList<Modeldata> objects) {
            super(context, 0, objects);
            this.context=context;
            this.objects=objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.item_list_view,null);
                Modeldata modeldata = objects.get(position);
                TextView name=convertView.findViewById(R.id.name);
                TextView year = convertView.findViewById(R.id.year);
                TextView color = convertView.findViewById(R.id.color);



                name.setText(modeldata.name);
                year.setText(modeldata.year);
                color.setText(modeldata.color);

            }


            return convertView;
        }
    }
}
