package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText edtname, edtheight, edtweight, edtBMI, edtdiagnosis;
    Button btnBMI;
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

        edtname = findViewById(R.id.edtname);
        edtheight = findViewById(R.id.edtheight);
        edtweight = findViewById(R.id.edtweight);
        edtBMI = findViewById(R.id.editBMI);
        edtdiagnosis = findViewById(R.id.edtdiagnosis);
        btnBMI = findViewById(R.id.btnBMI);
        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double H = Double.parseDouble(edtheight.getText()+"");
                double W = Double.parseDouble(edtweight.getText()+"");
                double BMI=W/Math.pow(H,2);
                String diagnosis="";
                if(BMI<18)
                {
                    diagnosis="Bạn gầy";
                }
                else if (BMI <= 24.9)
                {
                    diagnosis="Bạn bình thường";
                }
                else if(BMI <= 29.9)
                {
                    diagnosis="Bạn béo phì độ 1";
                }
                else if(BMI <= 34.9)
                {
                    diagnosis="Bạn béo phì độ 1";
                }
                else
                {
                    diagnosis="Bạn béo phì độ 3";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                edtBMI.setText(dcf.format(BMI));
                edtdiagnosis.setText(diagnosis);
            }
        });
    }
}