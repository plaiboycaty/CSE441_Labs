package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;
import android.net.Uri;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv1;
    ArrayList<List> mylist;
    MyArrayAdapter myadapter;
    String URL = "https://vnexpress.net/rss/tin-moi-nhat.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<>();
        myadapter = new MyArrayAdapter(this, R.layout.layout_listview, mylist);
        lv1.setAdapter(myadapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                String link = mylist.get(position).getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });

        new LoadRSS().execute();
    }

    class LoadRSS extends AsyncTask<Void, Void, ArrayList<List>> {
        @Override
        protected ArrayList<List> doInBackground(Void... voids) {
            ArrayList<List> tempList = new ArrayList<>();
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser parser = factory.newPullParser();

                XMLParser xmlParser = new XMLParser();
                String xml = xmlParser.getXmlFromUrl(URL);
                parser.setInput(new StringReader(xml));

                String title = "", link = "", description = "", imageUrl = "", info = "";
                Bitmap bitmap = null;
                boolean insideItem = false;

                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tagName = parser.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (tagName.equalsIgnoreCase("item")) {
                                insideItem = true;
                            } else if (insideItem) {
                                if (tagName.equalsIgnoreCase("title")) {
                                    title = parser.nextText();
                                } else if (tagName.equalsIgnoreCase("link")) {
                                    link = parser.nextText();
                                } else if (tagName.equalsIgnoreCase("description")) {
                                    description = parser.nextText();
                                    try {
                                        imageUrl = description.substring(description.indexOf("src=\"") + 5, description.indexOf("\"", description.indexOf("src=\"") + 5));
                                        info = description.substring(description.indexOf("</br>") + 5);
                                        URL imageURL = new URL(imageUrl);
                                        bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (tagName.equalsIgnoreCase("item")) {
                                insideItem = false;
                                tempList.add(new List(bitmap, title, info, link));
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tempList;
        }

        @Override
        protected void onPostExecute(ArrayList<List> result) {
            super.onPostExecute(result);
            mylist.clear();
            mylist.addAll(result);
            myadapter.notifyDataSetChanged();
        }
    }
}
