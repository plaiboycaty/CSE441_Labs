package com.example.customlistview1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String phone[] = {"Điện thoại Iphone 13", "Điện thoại SamSung S24", "Điện thoại Nokia 6",
            "Điện thoại LG", "Điện thoại HTC", "Điện thoại Vsmart Joy2"};
    int imgphone[] = {R.drawable.ip, R.drawable.samsung, R.drawable.nokia,
            R.drawable.lg, R.drawable.htc, R.drawable.vsmart};
    ArrayList<Phone> mylist;
    MyArrayAdapter myadapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        for(int i = 0; i <phone.length; i++)
        {
            mylist.add(new Phone(phone[i], imgphone[i]));
        }
        myadapter = new MyArrayAdapter(this, R.layout.layout_listview, mylist);
        lv.setAdapter(myadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(MainActivity.this, SubActivity.class);
                myintent.putExtra("name", phone[position]);
                startActivity(myintent);
            }
        });
    }
}