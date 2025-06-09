package com.example.arsyntask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextDad;
    public MyAsyncTask(Activity ctd) {
        this.contextDad = ctd;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextDad, "onPreExecute called", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        // Simulate some background work
        for (int i = 0; i <= 100; i++) {
            {
                SystemClock.sleep(100);
                publishProgress(i); // Update progress
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // Update UI with progress values
        ProgressBar paDad = (ProgressBar) contextDad.findViewById(R.id.progressBar1);
        int value = values[0];
        paDad.setProgress(value);
        TextView txt1 = (TextView) contextDad.findViewById(R.id.textView1);
        txt1.setText(value + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Task completed, update UI accordingly
        Toast.makeText(contextDad, "Update xong roi do!", Toast.LENGTH_SHORT).show();
    }
}
