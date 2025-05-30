package com.example.sharepreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb, edtresult;
    Button btnsum, btnclear;
    TextView txthistory;
    String history = "";
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

        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtresult = findViewById(R.id.edtresult);
        btnsum = findViewById(R.id.btnsum);
        btnclear = findViewById(R.id.btnclear);
        txthistory = findViewById(R.id.txthistory);
        SharedPreferences myprefs = getSharedPreferences("mysave",MODE_PRIVATE);
        history = myprefs.getString("ls","");
        txthistory.setText(history);
        btnsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int result = a + b;
                edtresult.setText(result+"");
                history += a+" + "+b+" = "+result;
                txthistory.setText(history);
                history +="\n";
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history ="";
                txthistory.setText(history);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        SharedPreferences.Editor myedit = myprefs.edit();
        myedit.putString("ls", history);
        myedit.commit();
    }
}