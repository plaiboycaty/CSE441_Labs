package com.example.tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static  String[] arrayName =  {"Ảnh 1","Ảnh 2","Ảnh 3","Ảnh 4","Ảnh 5","Ảnh 6",
                                            "Ảnh 7","Ảnh 8","Ảnh 9","Ảnh 10", "Ảnh 11","Ảnh 12"};
    public static int[] ImageName = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,
                                    R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pic10,R.drawable.pic11,R.drawable.pic12};
    GridView gridViewDemo;
    MyarrayAdapter adapterList;
    ArrayList<Image> arrimage;
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

        gridViewDemo = findViewById(R.id.grid1);
        arrimage = new ArrayList<Image>();
        //Khởi tạo đối tượng và gán Data source
        adapterList = new MyarrayAdapter(MainActivity.this, R.layout.listitem, arrimage);
        gridViewDemo.setAdapter(adapterList);
        //gán adapter vào gridview
        //duyệt danh sách vào mảng dữ liệu
        for(int i = 0; i < ImageName.length; i++) {
            Image myimage = new Image();
            myimage.setName(arrayName[i]);
            myimage.setImg(ImageName[i]);
            arrimage.add(myimage);
            //gọi hàm cập nhật giao diện
            adapterList.notifyDataSetChanged();
        }
        //Viết sự kiện khi click vào đối tượng trong clickView
        gridViewDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(MainActivity.this, SubActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("TITLE", position);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
    }
}