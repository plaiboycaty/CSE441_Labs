package com.example.dbbrowser;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases" ;
    SQLiteDatabase database = null;
    String DATABASE_NAME="qlsach.db";
    //Khai báo Listview
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
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

        //Gọi hàm Copy CSDL t assets vào thư mục Databases
        processCopy();
        //Mở CSDL lên để dùng
        database = openOrCreateDatabase("qlsach.db", MODE_PRIVATE,null);
        //Tạo ListView
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);
        //Truy vấn CSDL và hiển thị lên Listview
        Cursor c = database.query("tbsach",null,null,null,null,null,null);
        c.moveToFirst();
        String data = "";
        while (c.isAfterLast() == false)
        {
            data = c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2);
            mylist.add(data);
            c.moveToNext();
        }
        c.close();
        myadapter.notifyDataSetChanged(); //Dữ liệu đã thay đổi, cần phải cập nhật lại giao diện hiển thị
    }

    private void processCopy(){
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try {
                CopyDataBaseFromAssets();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public void CopyDataBaseFromAssets() {
        try {
            InputStream myinput;
            myinput = getAssets().open(DATABASE_NAME);
            //Path to the just created empty db
            String outFileName = getDatabasePath();
            //if the path doesnt exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
            //Open the empty dv as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            //Truyền bytes dữ liệu từ input đến output
            int size = myinput.available();
            byte[] buffer = new byte[size];
            myinput.read(buffer);
            myOutput.write(buffer);
            //Close the streams
            myOutput.flush();
            myOutput.close();
            myinput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}