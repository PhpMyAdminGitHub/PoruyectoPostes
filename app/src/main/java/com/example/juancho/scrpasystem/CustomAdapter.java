package com.example.juancho.scrpasystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CustomAdapter extends ArrayAdapter<String> {

    Context context;
    String [] names;
    int [] imgenes;


    public CustomAdapter(Context context,  String[] names, int[] imgenes) {
        super(context, R.layout.spinner_item);
        this.context = context;
        this.names = names;
        this.imgenes = imgenes;
    }

    @Override
    public View getDropDownView(int position,  View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);



    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        return super.getView(position, convertView, parent);



    }

}

