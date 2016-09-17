package thani.labs.com.uwaterloorec;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import thani.labs.com.uwaterloorec.model.ScheduleEntry;
import thani.labs.com.uwaterloorec.provider.ScheduleProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            List<ScheduleEntry> data=new ArrayList<>();
            try {
                JSONArray jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++) {
                    JSONObject jsonScheduleData = jArray.getJSONObject(i);
                    ScheduleEntry scheduleEntry = new ScheduleEntry(jsonScheduleData.getString("start_time"),
                            jsonScheduleData.getString("end_time"), jsonScheduleData.getString("name"),
                            jsonScheduleData.getString("location"));
                    data.add(scheduleEntry);
                }

                RecyclerView rv = (RecyclerView) findViewById(R.id.schedule_list);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rv.setAdapter(new QuizListAdapter(data));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
