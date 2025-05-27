package com.example.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtid, edtname, edtnum;
    Button btninsert, btnupdate, btndelete, btnquery;
    ListView lv1;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
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

        edtid = findViewById(R.id.edtid);
        edtname = findViewById(R.id.edtname);
        edtnum = findViewById(R.id.edtnum);
        btninsert = findViewById(R.id.btninsert);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);
        btnquery = findViewById(R.id.btnquery);
        //Tạo ListView
        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv1.setAdapter(myadapter);
        //Tạo vả mở CSDL SQlite
        mydatabase = openOrCreateDatabase("qlsinhvien.db",MODE_PRIVATE,null);
        //Tạo Table để chứa dữ liệu
        try {
            String sql = "CREATE TABLE tbllop(malop TEXT primary key ,tenlop TEXT, siso INTEGER)";
            mydatabase.execSQL(sql);
        }
        catch (Exception e)
        {
            Log.e("Error", "Table đã tồn tại");
        }
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtid.getText().toString();
                String name = edtname.getText().toString();
                int num = Integer.parseInt(edtnum.getText().toString());
                ContentValues myvalue = new ContentValues();
                myvalue.put("malop", id);
                myvalue.put("tenlop", name);
                myvalue.put("siso", num);
                String msg = "";
                if (mydatabase.insert("tbllop",null,myvalue) == -1)
                {
                    msg = "Fail to Insert Record!";
                }
                else {
                    msg = "Insert record Sucessfully";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtid.getText().toString();
                int n = mydatabase.delete("tbllop","malop = ?", new String[]{id});
                String msg = "";
                if (n == 0)
                {
                    msg = "No record to Delete";
                }
                else {
                    msg = n +"Record is deleted";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(edtnum.getText().toString());
                String id = edtid.getText().toString();
                String name = edtname.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("tenlop", name);
                myvalue.put("siso", num);
                int n = mydatabase.update("tbllop", myvalue, "malop = ?", new String[]{id});
                String msg = "";
                if (n == 0)
                {
                    msg = "No record to Update";
                }
                else
                {
                    msg = n+ "Record is updated";
                }
                Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.clear();
                Cursor c = mydatabase.query("tbllop", null, null, null, null, null, null);
                if (c.moveToFirst()) {  // Di chuyển tới bản ghi đầu tiên
                    do {
                        String data = c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2);
                        mylist.add(data);
                    } while (c.moveToNext());  // Tiếp tục cho tới hết bản ghi
                }
                c.close();
                myadapter.notifyDataSetChanged();
            }
        });

    }
}