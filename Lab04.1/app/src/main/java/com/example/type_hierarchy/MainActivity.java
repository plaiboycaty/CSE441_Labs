package com.example.type_hierarchy;

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
    EditText edtFar, edtCel;
    Button btnFar , btnCel, btnClear;
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

        edtFar = findViewById(R.id.txtFar);
        edtCel = findViewById(R.id.txtCel);
        btnCel = findViewById(R.id.btnCel);
        btnFar = findViewById(R.id.btnFar);
        btnClear = findViewById(R.id.btnClear);
        btnFar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dcf = new DecimalFormat("#.00");
                String doF = edtFar.getText()+"";
                int F = Integer.parseInt(doF);
                edtCel.setText(""+dcf.format((F-32)/1.8));
            }
        });
        btnCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dcf = new DecimalFormat("#.00");
                String doC = edtCel.getText()+"";
                int C = Integer.parseInt(doC);
                edtFar.setText(""+dcf.format(C*1.8+32));
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCel.setText("");
                edtFar.setText("");
            }
        });
    }
}