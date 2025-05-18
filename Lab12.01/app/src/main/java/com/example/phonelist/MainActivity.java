package com.example.phonelist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ListView lv1;
    TextView txt1;
    String[] names = {"Iphone 7", "SamSung Galaxy S7", "Nokia Lumia 730", "Sony Xperia XZ", "HTC One E9"};
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
        lv1 = findViewById(R.id.lv1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        lv1.setAdapter(adapter);
    //  lv1.setOnItemClickListener((parent, view, position, id) -> {
    //        String selectedName = names[position];
    //        Toast.makeText(this, "Bạn chọn: " + selectedName, Toast.LENGTH_SHORT).show();
    //    });
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt1.setText("Vị trí "+ position + ": "+ names[position] );
            }
        });
    }
}