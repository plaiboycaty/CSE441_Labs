package com.example.demo_constraint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo các biến giao diện tại đây
    EditText edtA, edtB, edtResult;
    Button btnsum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Ánh xạ id cho các biến giao diện
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtResult = findViewById(R.id.edtResult);
        btnsum = findViewById(R.id.btnsum);
        //Xử lí tương tác với người dùng
        //Click vào button có sự kiện
        btnsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtA.getText().toString()); //Lấy dữ liệu từ edtA xong ép sang kiểu int
                int b = Integer.parseInt(edtB.getText().toString());
                int c = a + b;
                edtResult.setText(c+""); //Hiển thị kết quả nhưng set chỉ nhận kiểu string
                //Nên ép kiểu int sang string bằng 1 chuỗi rỗng
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}