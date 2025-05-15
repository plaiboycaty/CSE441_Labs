package com.example.intent3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    EditText edtaa, edtbb;
    Button btnsum, btndiff;
    Intent myintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtaa = findViewById(R.id.edtaa);
        edtbb = findViewById(R.id.edtbb);
        btnsum = findViewById(R.id.btnsum);
        btndiff = findViewById(R.id.btndiff);
        //Nhận intent
        myintent = getIntent();
        //Lấy dữ liệu từ intent
        int a = myintent.getIntExtra("numa",0);
        int b = myintent.getIntExtra("numb", 0);
        edtaa.setText(a+"");
        edtbb.setText(b+"");
        btnsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý kết quả
                int kq = a + b;
                //Đẩy kết quả trở lại intent
                myintent.putExtra("kq",kq);
                //Trả intent trở về
                setResult(33,myintent);
                //Thoát activity
                finish();
            }
        });
        btndiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý kết quả
                int kq = a - b;
                myintent.putExtra("kq",kq);
                setResult(34,myintent);
                finish();
            }
        });
    }
}