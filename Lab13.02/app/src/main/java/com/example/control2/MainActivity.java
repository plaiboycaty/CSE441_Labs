package com.example.control2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView txt1;
    GridView gridView1;
    String arr[] = {"Ipad", "Iphone", "New Ipad", "SamSung", "Nokia",
    "Sony Ericson", "LG", "Q_Mobile", "HTC",
            "Blackberry", "G Phone", "FPT - Phone","HK Phone" };

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

        txt1 = findViewById(R.id.txt1);
        gridView1 = findViewById(R.id.gridView1);
        ArrayAdapter<String>da= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        gridView1.setAdapter(da);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt1.setText(arr[position]);
            }
        });
    }
}