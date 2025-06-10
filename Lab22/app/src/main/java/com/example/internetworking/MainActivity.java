package com.example.internetworking;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends Activity {

    Button btnparse;
    ListView lv1;
    ArrayAdapter<String> myadapter;
    ArrayList<String> mylist;
    String URL = "https://api.androidhive.info/pizza/?format=xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnparse = findViewById(R.id.btn);
        lv1 = findViewById(R.id.lv);

        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mylist);
        lv1.setAdapter(myadapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadExampleTask().execute();
            }
        });
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> list = new ArrayList<>();
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();

                XMLParser myparser = new XMLParser();
                String xml = myparser.getXmlFromUrl(URL);

                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();
                String nodeName;
                String datashow = "";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("id")) {
                                datashow += parser.nextText() + "-";
                            } else if (nodeName.equals("name")) {
                                datashow += parser.nextText();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("item")) {
                                list.add(datashow);
                                datashow = "";
                            }
                            break;
                    }
                    eventType = parser.next();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            myadapter.clear();
            myadapter.addAll(result);
        }
    }
}
