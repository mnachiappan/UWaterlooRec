package thani.labs.com.uwaterloorec;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import thani.labs.com.uwaterloorec.provider.ScheduleProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.quiz_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new QuizListAdapter(new ScheduleProvider().readQuizzes()));
        new AsyncFetch().execute();
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {

        URL url = null;
        HttpsURLConnection conn;

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Your Message", Toast.LENGTH_LONG).show();
        }


        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL("https://dog.mnachiappan.com/data.json");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                conn = (HttpsURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }

            try {
                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    // Pass data to onPostExecute method
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
}
