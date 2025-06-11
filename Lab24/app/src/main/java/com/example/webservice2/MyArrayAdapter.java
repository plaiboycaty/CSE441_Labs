package com.example.webservice2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Tygia> {

    Activity context;
    int resource;
    List<Tygia> objects;
    public MyArrayAdapter(Activity context, int resource, List<Tygia> objects) {
        super(context, resource, objects);
// TODO Auto-generated constructor stub
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        Tygia tygia = this.objects.get(position);
        ImageView imgHinh = (ImageView) item.findViewById(R.id.img_h1);
        TextView txtType = (TextView) item.findViewById(R.id.txt_type);
        TextView txtMuaTM = (TextView) item.findViewById(R.id.txt_buytm);
        TextView txtBanTM = (TextView) item.findViewById(R.id.txt_selltm);
        TextView txtMuaCK = (TextView) item.findViewById(R.id.txt_buyck);
        TextView txtBanCK = (TextView) item.findViewById(R.id.txt_sellck);
        imgHinh.setImageBitmap(tygia.getBitmap());
        txtType.setText(tygia.getType());
        txtMuaTM.setText(tygia.getBuycash());
        txtBanTM.setText(tygia.getSellcash());
        txtMuaCK.setText(tygia.getBuyck());
        txtBanCK.setText(tygia.getSellck());
        return item;

    }
}

