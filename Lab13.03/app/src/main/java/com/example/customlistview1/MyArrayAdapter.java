package com.example.customlistview1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int idlayout;
    ArrayList<Phone> mylist;

    public MyArrayAdapter(Activity context, int idlayout, ArrayList<Phone> mylist) {
        super(context, idlayout, mylist);
        this.context = context;
        this.idlayout = idlayout;
        this.mylist = mylist;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        LayoutInflater myInflactor = context.getLayoutInflater();
        convertView = myInflactor.inflate(idlayout, null);
        Phone myphone = mylist.get(position);
        ImageView imgphone = convertView.findViewById(R.id.imagephone);
        TextView txt1 = convertView.findViewById(R.id.txt1);
        txt1.setText(myphone.getNamephone());
        imgphone.setImageResource(myphone.getImgphone());
        return convertView;
    }
}
