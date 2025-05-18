package com.example.note;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lv1;
    EditText edtwork, edthour, edtmin;
    TextView txtdate;
    Button btnadd;
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
        lv1 = findViewById(R.id.lv1);
        edtwork = findViewById(R.id.edtwork);
        edthour = findViewById(R.id.edthour);
        edtmin = findViewById(R.id.edtmin);
        btnadd = findViewById(R.id.btnadd);
        txtdate = findViewById(R.id.txtdate);
        //Khai báo arrayList
        ArrayList<String> arraywork = new ArrayList<>();
        //Khai báo arrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arraywork);
        //Đưa Adapter có dữ liệu lên Listview
        lv1.setAdapter(arrayAdapter);
        //Lấy ngày giờ theo hệ thống
        Date currentdate = Calendar.getInstance().getTime();
        //Format theo định dạng dd/mm/yyyy;
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        //Hiển thị lên TextView
        txtdate.setText("Hôm nay: "+simpleDateFormat.format(currentdate));
        //Thêm sự kiện vào button
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo dialog nếu 1 trong 3 edittext không có nội dung
                if(edtwork.getText().toString().equals("")||edthour.getText().toString().equals("")
                ||edtmin.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }
                else {
                    String str ="+ " + edtwork.getText().toString() + " - "+ edthour.getText().toString() + ": "+edtmin.getText().toString();
                    //thêm dữ liệu cho mảng
                    arraywork.add(str);             //Xóa arraywork.remove(i);
                    //Cập nhật lại Adapter
                    arrayAdapter.notifyDataSetChanged();
                    edthour.setText("");
                    edtmin.setText("");
                    edtwork.setText("");
                }
            }
        });
    }
}